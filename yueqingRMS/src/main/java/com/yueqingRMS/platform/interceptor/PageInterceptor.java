package com.yueqingRMS.platform.interceptor;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;  
import org.apache.ibatis.executor.statement.StatementHandler;  
import org.apache.ibatis.mapping.BoundSql;  
import org.apache.ibatis.mapping.MappedStatement;  
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;  
import org.apache.ibatis.plugin.Intercepts;  
import org.apache.ibatis.plugin.Invocation;  
import org.apache.ibatis.plugin.Plugin;  
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;

import com.yueqingRMS.platform.exception.AppBusinessException;
import com.yueqingRMS.platform.tools.page.Page;
  
/**  
 * properties?, settings?, typeAliases?, typeHandlers?, objectFactory?, objectWrapperFactory?, plugins?, environments?, databaseIdProvider?, mappers?  
 * 分页拦截器，用于拦截需要进行分页查询的操作，然后对其进行分页处理。  
 * 利用拦截器实现Mybatis分页的原理：  
 * 要利用JDBC对数据库进行操作就必须要有一个对应的Statement对象，Mybatis在执行Sql语句前就会产生一个包含Sql语句的Statement对象，而且对应的Sql语句  
 * 是在Statement之前产生的，所以我们就可以在它生成Statement之前对用来生成Statement的Sql语句下手。在Mybatis中Statement语句是通过RoutingStatementHandler对象的  
 * prepare方法生成的。所以利用拦截器实现Mybatis分页的一个思路就是拦截StatementHandler接口的prepare方法，然后在拦截器方法中把Sql语句改成对应的分页查询Sql语句，之后再调用  
 * StatementHandler对象的prepare方法，即调用invocation.proceed()。  
 * 对于分页而言，在拦截器里面我们还需要做的一个操作就是统计满足当前条件的记录一共有多少，这是通过获取到了原始的Sql语句后，把它改为对应的统计语句再利用Mybatis封装好的参数和设  
 * 置参数的功能把Sql语句中的参数进行替换，之后再执行查询记录数的Sql语句进行总记录数的统计。  
 *  
 */  
@Lazy(false)
@Intercepts({
		 //mybatis.3.3.jar用
//		 @Signature(type=StatementHandler.class,method="prepare",args={Connection.class}),
		 //mybatis.3.4.5.jar用
		 @Signature(type=StatementHandler.class,method="prepare",args={Connection.class, Integer.class }),
		 @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
			        											  RowBounds.class, ResultHandler.class })
})  
public class PageInterceptor implements Interceptor {  
	
	private static final Logger log = LoggerFactory.getLogger(PageInterceptor.class);
    private String dialect = ""; //数据库方言    
    private String pageSqlId = ""; //mapper.xml中需要拦截的ID(正则匹配) 
    public static final ThreadLocal<Page> localPage = new ThreadLocal<Page>();  
        
	public Object intercept(Invocation invocation) {  
    	
        //对于StatementHandler其实只有两个实现类，一个是RoutingStatementHandler，另一个是抽象类BaseStatementHandler，    
        //BaseStatementHandler有三个子类，分别是SimpleStatementHandler，PreparedStatementHandler和CallableStatementHandler，    
        //SimpleStatementHandler是用于处理Statement的，PreparedStatementHandler是处理PreparedStatement的，而CallableStatementHandler是    
        //处理CallableStatement的。Mybatis在进行Sql语句处理的时候都是建立的RoutingStatementHandler，而在RoutingStatementHandler里面拥有一个    
        //StatementHandler类型的delegate属性，RoutingStatementHandler会依据Statement的不同建立对应的BaseStatementHandler，即SimpleStatementHandler、    
        //PreparedStatementHandler或CallableStatementHandler，在RoutingStatementHandler里面所有StatementHandler接口方法的实现都是调用的delegate对应的方法。    
        //我们在PageInterceptor类上已经用@Signature标记了该Interceptor只拦截StatementHandler接口的prepare方法，又因为Mybatis只有在建立RoutingStatementHandler的时候    
        //是通过Interceptor的plugin方法进行包裹的，所以我们这里拦截到的目标对象肯定是RoutingStatementHandler对象。  
       
		
		
        
        
    	if(invocation.getTarget() instanceof RoutingStatementHandler){ 

    		//Mybatis在进行Sql语句处理的时候都是建立的RoutingStatementHandler
    		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
    		BoundSql boundSql = statementHandler.getBoundSql();
            Object obj = boundSql.getParameterObject(); 
            //mybatis.3.4.5.jar用
            MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, 
            		SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,new DefaultReflectorFactory());
            MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
//            Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
//            pageSqlId = configuration.getVariables().getProperty("pageSqlId");
//            if (obj instanceof Page) {
            if (mappedStatement.getId().matches(pageSqlId)) {
            	Page page = (Page)obj;
//            Page page = localPage.get();
//            if (page != null) {
            	 // 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
                metaStatementHandler.setValue("delegate.rowBounds.offset", 
                RowBounds.NO_ROW_OFFSET);
                metaStatementHandler.setValue("delegate.rowBounds.limit", 
                RowBounds.NO_ROW_LIMIT);
                
            	//将含有参数的对象保存在ThreadLocal中缓存
                Connection connection = (Connection)invocation.getArgs()[0];    
                //获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句    
                String sql = boundSql.getSql();    
                //给当前的page参数对象设置总记录数    
                this.setTotalRecord(page,    
                       mappedStatement, connection);    
                //获取分页Sql语句    
                String pageSql = this.getPageSql(page, sql);    
                //重写分页sql  
                metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
            }   
            
			try {
				return invocation.proceed();
			} catch (InvocationTargetException | IllegalAccessException e) {
				//清除本次分页占用内存
//				endPage();
				throw new AppBusinessException("分页拦截执行异常", e);
			}
			
        }else if (invocation.getTarget() instanceof Executor) {
        	
//        	Object parameter = invocation.getArgs()[1];
//            Object obj = seekPage(parameter);
//            Object result;
			try {
				return invocation.proceed();  
				// 查询结果的地方
//	            if (!(obj instanceof Page)) { 
//		            return invocation.proceed();  
//		        }else {
//		        	Page page = (Page) obj;
//	            	//将含有参数的对象保存在ThreadLocal中缓存
//	            	startPage(page);
//		        	result = invocation.proceed();
//		            page.addAll((List<?>) result);  
//		            return page;
//		        }
				
			} catch (InvocationTargetException | IllegalAccessException e) {
				throw new AppBusinessException("分页拦截执行异常", e);
			}
//			finally{
//				//清除本次分页占用内存
//				endPage();
//			}
        }
		return invocation;
       
    }  
      
    /**  
     * 给当前的参数对象page设置总记录数  
     *  
     * @param page Mapper映射语句对应的参数对象  
     * @param mappedStatement Mapper映射语句  
     * @param connection 当前的数据库连接  
     */    
    private void setTotalRecord(Page page,    
           MappedStatement mappedStatement, Connection connection) {    
       //获取对应的BoundSql，这个BoundSql其实跟我们利用StatementHandler获取到的BoundSql是同一个对象。    
       //delegate里面的boundSql也是通过mappedStatement.getBoundSql(paramObj)方法获取到的。    
       BoundSql boundSql = mappedStatement.getBoundSql(page);    
       //获取到我们自己写在Mapper映射语句中对应的Sql语句    
       String sql = boundSql.getSql();    
       //通过查询Sql语句获取到对应的计算总记录数的sql语句    
       String countSql = this.getCountSql(sql);    
       //通过BoundSql获取对应的参数映射    
       List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();    
       //利用Configuration、查询记录数的Sql语句countSql、参数映射关系parameterMappings和参数对象page建立查询记录数对应的BoundSql对象。    
       BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, page);    
       //通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象    
       ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, countBoundSql);    
       //通过connection建立一个countSql对应的PreparedStatement对象。    
       PreparedStatement pstmt = null;    
       ResultSet rs = null;    
       try {    
           pstmt = connection.prepareStatement(countSql);    
           //通过parameterHandler给PreparedStatement对象设置参数    
           parameterHandler.setParameters(pstmt);    
           //之后就是执行获取总记录数的Sql语句和获取结果了。    
           rs = pstmt.executeQuery();    
           if (rs.next()) {    
              int totalRecord = rs.getInt(1);
              if (log.isDebugEnabled()) {
                  log.debug("结果集长度: " + totalRecord);
              }
              //给当前的参数page对象设置总记录数    
              page.setTotalRecord(totalRecord);    
           }    
       } catch (SQLException e) {    
           e.printStackTrace();    
       } finally {    
           try {    
              if (rs != null)    
                  rs.close();    
               if (pstmt != null)    
                  pstmt.close();    
           } catch (SQLException e) {    
              e.printStackTrace();    
           }    
       }    
    }    
      
    /**  
     * 根据原Sql语句获取对应的查询总记录数的Sql语句  
     * @param sql  
     * @return  
     */    
    private String getCountSql(String sql) {    
       int index = sql.indexOf("from");    
       if (log.isDebugEnabled()) {
           log.debug("结果集长度SQL: " + "select count(*) " + sql.substring(index));
       }
       return "select count(*) " + sql.substring(index);    
    }    
      
    /**  
     * 根据page对象获取对应的分页查询Sql语句，这里只做了两种数据库类型，Mysql和Oracle  
     * 其它的数据库都 没有进行分页  
     *  
     * @param page 分页对象  
     * @param sql 原sql语句  
     * @return  
     */    
    private String getPageSql(Page page, String sql) {    
       StringBuffer sqlBuffer = new StringBuffer(sql);    
       if ("mysql".equalsIgnoreCase(dialect)) {    
           return getMysqlPageSql(page, sqlBuffer);    
       } else if ("oracle".equalsIgnoreCase(dialect)) {    
           return getOraclePageSql(page, sqlBuffer);    
       }    
       return sqlBuffer.toString();    
    }    
      
    /**  
    * 获取Mysql数据库的分页查询语句  
    * @param page 分页对象  
    * @param sqlBuffer 包含原sql语句的StringBuffer对象  
    * @return Mysql数据库分页语句  
    */    
   private String getMysqlPageSql(Page page, StringBuffer sqlBuffer) {    
      //计算第一条记录的位置，Mysql中记录的位置是从0开始的。    
//     System.out.println("page:"+page.getPage()+"-------"+page.getRows());  
      int offset = (page.getPage() - 1) * page.getPageSize();  
//      int offset = (page.getPage() ) * page.getPageSize();  
      sqlBuffer.append(" limit ").append(offset).append(",").append(page.getPageSize());  
      if (log.isDebugEnabled()) {
          log.debug("分页SQL: " + sqlBuffer.toString());
      }
      return sqlBuffer.toString();    
   }    
      
   /**  
    * 获取Oracle数据库的分页查询语句  
    * @param page 分页对象  
    * @param sqlBuffer 包含原sql语句的StringBuffer对象  
    * @return Oracle数据库的分页查询语句  
    */    
   private String getOraclePageSql(Page page, StringBuffer sqlBuffer) {    
      //计算第一条记录的位置，Oracle分页是通过rownum进行的，而rownum是从1开始的    
      int offset = (page.getPage() - 1) * page.getPageSize() + 1;  
//      int offset = (page.getPage() ) * page.getPageSize() + 1;  
      sqlBuffer.insert(0, "select u.*, rownum r from (").append(") u where rownum < ").append(offset + page.getPageSize());    
      sqlBuffer.insert(0, "select * from (").append(") where r >= ").append(offset);    
      //上面的Sql语句拼接之后大概是这个样子：    
      //select * from (select u.*, rownum r from (select * from t_user) u where rownum < 31) where r >= 16    
      if (log.isDebugEnabled()) {
          log.debug("分页SQL: " + sqlBuffer.toString());
      }
      return sqlBuffer.toString();    
   }    
     
        
    /**  
     * 拦截器对应的封装原始对象的方法  
     */          
    public Object plugin(Object target) {    
        // TODO Auto-generated method stub    
        if (target instanceof StatementHandler || target instanceof  Executor) {    
            return Plugin.wrap(target, this);    
        } else {    
            return target;    
        }   
    }    
    
    
    /** 
     * 将含有参数的对象保存在ThreadLocal中缓存，便于在不同步骤中取用
     * 开始分页 
     * @param pageNum 
     * @param pageSize 
     */  
    public static void startPage(Page page) { 
        localPage.set(page);  
    }  
  
    /** 
     * 结束分页并返回结果，该方法必须被调用，否则localPage会一直保存下去，直到下一次startPage 
     * @return 
     */  
    @SuppressWarnings("unused")
	public static void endPage() {  
        Page page = localPage.get();  
        localPage.remove();  
//        return page;  
    }
    
    /**
     * 寻找page对象
     * <p>
     *
     * @param parameter
    */
    @SuppressWarnings("rawtypes")
    protected Page seekPage(Object parameter) {
        Page page = null;
        if (parameter == null) {
            return null;
        }
        if (parameter instanceof Page) {
            page = (Page) parameter;
        } else if (parameter instanceof Map) {
            Map map = (Map) parameter;
            for (Object arg : map.values()) {
                if (arg instanceof Page) {
                    page = (Page) arg;
                }
            }
        }
        return page;
    }
    
    /**  
     * 设置注册拦截器时设定的属性  
     */   
    public void setProperties(Properties p) {  
          
    }  
  
    /**
     * @return
     */
    public String getDialect() {  
        return dialect;  
    }  
  
    /**
     * @param dialect
     */
    public void setDialect(String dialect) {  
        this.dialect = dialect;  
    }  
  
    /**
     * @return
     */
    public String getPageSqlId() {  
        return pageSqlId;  
    }  
  
    /**
     * @param pageSqlId
     */
    public void setPageSqlId(String pageSqlId) {  
        this.pageSqlId = pageSqlId;  
    }  
      
    
//  //通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象    
//  ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, boundSql);    
//  //通过connection建立一个countSql对应的PreparedStatement对象。    
//  PreparedStatement pstmt = null;    
//  ResultSet rs = null;    
//  try {    
//      pstmt = connection.prepareStatement(sql);    
//      //通过parameterHandler给PreparedStatement对象设置参数    
//      parameterHandler.setParameters(pstmt);    
//      //之后就是执行获取总记录数的Sql语句和获取结果了。    
//      rs = pstmt.executeQuery();    
//      
//      //handleResultSets的结果一定是一个List，当我们的对应的Mapper接口定义的是返回一个单一的元素，并且handleResultSets返回的列表  
//      //的size为1时，Mybatis会取返回的第一个元素作为对应Mapper接口方法的返回值。  
//      List<Object> resultList = new ArrayList<Object>(); 
//      for(;rs.next();) {
//      	rs.getMetaData().getColumnCount();
//      	resultList.add(rs.getString(1));
//      }  
//    page.addAll(resultList);
//    return page;
//  } catch (SQLException e) {    
//      e.printStackTrace();    
//  } finally {    
//      try {    
//         if (rs != null)    
//             rs.close();    
//          if (pstmt != null)    
//             pstmt.close();    
//      } catch (SQLException e) {    
//         e.printStackTrace();    
//      }    
//  }   
}  