<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<html>
	<head>
		<title>ECharts-GL Hello World</title>
		
		<!-- 引入 echarts.js -->
    	<%@ include file="/common/echarts/echarts.jsp"%>
		<script>
		// µÚ¶þ¸ö²ÎÊý¿ÉÒÔÖ¸¶¨Ç°ÃæÒýÈëµÄÖ÷Ìâ
		//var chart = echarts.init(document.getElementById('main'), 'macarons');
		//chart.setOption({
		//	...
		//});
		</script>
		
		
	
	</head>
	
	<body>
	
	
		<div id= "main"  style="width:1200px;height:800px;" ></div>
		<script>
		
		
		var option= {
           /* title : {
                subtext : 'ÈËÊý',
                subtextStyle : {
                    'color' : '#6E6E6E',
                    'fontStyle' : 'normal',
                    'fontWeight' : 'lighter',
                    'fontSize' : 14,
                    'verticalAlign' : 'middle',
                },
                left : '50%',
				top : '10px'
            },//±êÌâÊôÐÔ*/
			title:{
				 text:'²»Í¬ÀàÐÍ³¤Æª£¨500×ÖÒÔÉÏ£©¸öÊý¼°µãÔÞÒ»ÀÀ±í',
				 textStyle:{
					//ÎÄ×ÖÑÕÉ«
					color:'#ccc',
					//×ÖÌå·ç¸ñ,'normal','italic','oblique'
					fontStyle:'normal',
					//×ÖÌå´ÖÏ¸ 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
					fontWeight:'bold',
					//×ÖÌåÏµÁÐ
					fontFamily:'sans-serif',
					//×ÖÌå´óÐ¡
					fontSize:18,
					},
                  left : '20%',
				  top : '2px',
				  subtext : '¸±±êÌâ',
					subtextStyle : {
                    'color' : '#6E6E6E',
                    'fontStyle' : 'normal',
                    'fontWeight' : 'lighter',
                    'fontSize' : 14,
                    'verticalAlign' : 'middle',
					},
					itemGap:20 
			},
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // ×ø±êÖáÖ¸Ê¾Æ÷£¬×ø±êÖá´¥·¢ÓÐÐ§
                    type : 'shadow'        // Ä¬ÈÏÎªÖ±Ïß£¬¿ÉÑ¡Îª£º'line' | 'shadow'
                }
            },
            legend: {
                data: ['»Ø´ð¸öÊý', 'µãÔÞÁ¿'],
                left: '65%',//¾àÀë×ó²àµÄ°Ù·Ö±È
				top : '15px'
            },
            grid: {
                left: '10%',
                right: '10%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                name : 'Àà±ð',//xÖáËµÃ÷
                type: 'category',
				splitNumber: 20,
                data: ['ÇéÐ÷Êã·¢Àà','¹Ûµã³ÂÊöÀà','ÊÂÊµ½²ÊöÀà','·´Ë¼Àà','¹éÄÉ×Ü½áÀà'],
				//boundaryGap: ['20%', '20%'],
            },
            yAxis:  
			/*{
                type: 'value',
                splitLine:{
                    show:true,
                    lineStyle:{
                        color:'#DCDCDC',
                        width: 1,
                        type: 'dotted',//ÐéÏß

                    },
                },
                splitNumber: 10,//yÖá·Ö¸î¶ÎÊý
            }*/
			[
                    {  

                        type: 'value',
                      //  boundaryGap: ['20%', '20%'],
						splitNumber: 50,//yÖá·Ö¸î¶ÎÊý
                        axisTick:
                        {
                            show:false//²»ÏÔÊ¾YÖáµÄ¿Ì¶È
                        },
                        axisLine:
                        { // YÖáÏß...ÑÕÉ«
                            onZero: false,
                            lineStyle:
                            {
                                color:'#c490bf'
                            },  
                        },
                        axisLabel:
                        { // YÖáµÄ0 5 10...ÑÕÉ«
                            textStyle:
                            {
                                color:'#000',
                            }
                        },
                        //data: ['OAWEB01','OAWEB02', 'OADB01', 'OADB02', 'OALOG'],
                        position:'left',
                        name:'»Ø´ð¸öÊý',
                        nameLocation:'end',
                        nameTextStyle:
                        {
                            color:'#30ad3a',

                             padding:[0,80,0,0],
                        },


                    },
                    {  
                        type: 'value',  
                      //  boundaryGap: ['20%', '20%'],
						// splitNumber: 100,//yÖá·Ö¸î¶ÎÊý
                        axisTick:
                        {
                            show:false//²»ÏÔÊ¾YÖáµÄ¿Ì¶È
                        },
                        axisLine:
                        { // YÖáÏß...ÑÕÉ«
                            onZero: false,
                            lineStyle:
                            {
                                color:'#c490bf'
                            },  
                        },
                        axisLabel:
                        { // YÖáµÄ0 5 10...ÑÕÉ«
                            textStyle:
                            {
                                color:'#000',
                            }
                        },
                        //data: ['192.168.66.102', '192.168.66.103', '192.168.66.104', '192.168.66.105', '192.168.66.102'],
                        position:'right',
                        name:'µãÔÞÁ¿',
                        nameLocation:'end',

                        nameTextStyle:
                        {
                            color:'#30ad3a',
                            fontWeight:'normal',

                            padding:[0,0,0,200],

                        },
                        // position:'right',


                    },
                ]
			,
            series: [
                {
                    name: '»Ø´ð¸öÊý',
                    type: 'bar',
                    stack: '»Ø´ð¸öÊý',
                    barWidth : 50,
                    itemStyle:{
                        normal: {
                            //ÖùÐÎÍ¼Ô²½Ç£¬³õÊ¼»¯Ð§¹û
                            color:'#0099ff',
                            //barBorderRadius:[0, 10, 10, 0],
                            label: {
                                show: true,//ÊÇ·ñÕ¹Ê¾
                                textStyle: {
                                    fontWeight:'bolder',
                                    fontSize : '12',
                                    fontFamily : 'Î¢ÈíÑÅºÚ',
                                }
                            }
                        },
                    },
                    label: {
                        normal: {
                            show: true,
                            position: 'inside'
                        }
                    },
                    data: [6, 6, 7, 9, 8],
					barGap:'1%'
                },
                {
                    name: 'µãÔÞÁ¿',
                    type: 'bar',
                    stack: 'µãÔÞÁ¿',
                    barWidth : 50,
                    itemStyle:{
                        normal: {
                            //ÖùÐÎÍ¼Ô²½Ç£¬³õÊ¼»¯Ð§¹û
                            color:'#ff8855',
                            //barBorderRadius:[10, 10, 0, 0],
                            label: {
                                show: true,//ÊÇ·ñÕ¹Ê¾
                                textStyle: {
                                    fontWeight:'bolder',
                                    fontSize : '12',
                                    fontFamily : 'Î¢ÈíÑÅºÚ',
                                }
                            }
                        },
                    },
                    label: {
                        normal: {
                            show: true,
                            position: 'inside'
                        }
                    },
                    data: [20441/1000, 1606/1000, 7188/1000, 37624/1000, 5074/1000],
					barGap:'1%'
                },
            ]
        };
			
			
		// µÚ¶þ¸ö²ÎÊý¿ÉÒÔÖ¸¶¨Ç°ÃæÒýÈëµÄÖ÷Ìâ
		//var myChart = echarts.init(document.getElementById('main'), 'dark');
		var myChart = echarts.init(document.getElementById('main'));
		//¼ÓÔØ¶¯»­
		//myChart.showLoading();
		
		//Òþ²Ø¼ÒÔÚ¶¯»­
		//myChart.hideLoading();
		myChart.setOption(option);
		</script>
		
		
	
	
	</body>

</html>