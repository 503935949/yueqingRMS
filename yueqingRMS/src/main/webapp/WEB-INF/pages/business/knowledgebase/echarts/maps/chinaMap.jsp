<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <%@ include file="/common/echarts/echarts.jsp"%>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/china.js"></script>
	
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/beijing.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/tianjin.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/shanghai.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/chongqing.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/hebei.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/henan.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/yunnan.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/liaoning.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/heilongjiang.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/hunan.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/anhui.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/shandong.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/xinjiang.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/jiangsu.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/zhejiang.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/jiangxi.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/hubei.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/guangxi.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/gansu.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/shanxi.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/neimenggu.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/shanxi1.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/jilin.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/fujian.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/guizhou.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/guangdong.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/qinghai.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/xizang.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/sichuang.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/ningxia.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/hainan.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/taiwan.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/xianggang.js"></script>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/maps/aomen.js"></script>
	
	
	
</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width:1000px;height:600px;"></div>333
    <script type="text/javascript">
	
	//var chart = echarts.init(document.getElementById('main'));
   // chart.setOption({
      //  series: [{
     //       type: 'map',
     //       map: 'china'
     //   }]
   // });
        // 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
      var option = {
    backgroundColor:'#00bfff',
    tooltip : {
        trigger: 'item'
    },
    toolbox: {
        show : true,
        orient: 'horizontal',
        right:'left',
        top:'top',
        feature : {
            dataView: {readOnly: false},
           // magicType: {type: ['line', 'bar']},
           // restore: {},
            saveAsImage: {}
        }
    },
    series : [
        {
            tooltip: {
                trigger: 'item',
                formatter: '{b}'
            },
            name: '行业应用数量',
            type: 'map',
            map: 'china',
            left:'left',
            top: 'top',
            width: '75%',
           //roam: true,
            selectedMode : 'single',
            itemStyle:{
                normal: {
                    label:{show:true},
                    borderWidth: 2,
                    borderColor : '#eee',
                    shadowBlur: 10,
                    shadowColor: 'rgba(0, 0, 0, 0.2)'
                },
                //normal:{},
                emphasis:{label:{show:true}}
            },
            data:[
                {name: '北京', selected:false, value: 3},
                {name: '天津', selected:false, value: 3},
                {name: '上海', selected:false, value: 1},
                {name: '重庆', selected:false, value: 3},
                {name: '河北', selected:false, value: 3},
                {name: '河南', selected:false, value: 26},
                {name: '云南', selected:false, value: 33},
                {name: '辽宁', selected:false, value: 22},
                {name: '黑龙江', selected:false, value: 3},
                {name: '湖南', selected:false, value: 3},
                {name: '安徽', selected:false, value: 3},
                {name: '山东', selected:false, value: 2},
                {name: '新疆', selected:false, value: 0},
                {name: '江苏', selected:false, value: 0},
                {name: '浙江', selected:false, value: 0},
                {name: '江西', selected:false, value: 0},
                {name: '湖北', selected:false, value: 12},
                {name: '广西', selected:false, value: 3},
                {name: '甘肃', selected:false, value: 3},
                {name: '山西', selected:false, value: 27},
                {name: '内蒙古', selected:false, value: 3},
                {name: '陕西', selected:false, value: 3},
                {name: '吉林', selected:false, value: 3},
                {name: '福建', selected:false, value: 44},
                {name: '贵州', selected:false, value: 0},
                {name: '广东', selected:false, value: 13},
                {name: '青海', selected:false, value: 3},
                {name: '西藏', selected:false, value: 2},
                {name: '四川', selected:false, value: 0},
                {name: '宁夏', selected:false, value: 3},
                {name: '海南', selected:false, value: 3},
                {name: '台湾', selected:false, value: 3},
                {name: '香港', selected:false, value: 3},
                {name: '澳门', selected:false, value: 3}
            ]
        }
    ],
    animation: false,
	dataRange : {
        orient: 'horizontal',
        right: 'right',
        min: 0,
        max: 100,
        color:['orange','yellow','white'],
        text:['高','低'],           // 文本，默认为数值文本
        splitNumber:0
    }
};
$(function (){
//var ecConfig = require('echarts/config');
myChart.on('click', function (param){
    var selecteds = param.selected;
    var selectedProvince;
    var name;
    for (var i = 0, l = option.series[0].data.length; i < l; i++) {
        name = option.series[0].data[i].name;
		option.series[0].data[i].selected = selecteds;
        if (option.series[0].data[i].name == param.name) {
            selectedProvince = name;
        }
    }
    /*for (var i = 0, l = option.series[0].data.length; i < l; i++) {
        name = option.series[0].data[i].name;
        option.series[0].data[i].selected = selecteds;
        if (selecteds) {
            selectedProvince = name;
        }
    }*/
    if (typeof selectedProvince == 'undefined') {
        option.series.splice(1);
        option.legend = null;
        //option.dataRange = null;
        myChart.setOption(option, true);
        return;
    }
    option.series[1] = {
		
        name: '省市',
        type: 'map',
        map: selectedProvince,
        itemStyle:{
            normal:{label:{show:false},
					borderWidth: 2,
                    borderColor : '#eee',
                    shadowBlur: 10,
                    shadowColor: 'rgba(0, 0, 0, 0.2)',
					areaColor: '#0e1',
					},
            emphasis:{label:{show:true}}
        },
       // silent:true,
        right: 'right',
        center: 'center',
        width: '30%',
       
        //roam: true,
        data:[
			{name: '实例', selected:false, value: 3},
        ]
    };
    //option.legend = {
    //    left:'left',
    //    data:['行业应用数量']
   // };

    myChart.setOption(option,true);
})
                    

        // 使用刚指定的配置项和数据显示图表。
       // myChart.setOption(option);
	      myChart.setOption(option, true);
		  });
    </script>
</body>
</html>