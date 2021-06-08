var geoCoordMap = {
    '大连': [122.2229,39.4409],
    '深圳': [114.5435,22.5439],
    '上海': [121.4648, 31.2891],
    '新疆': [87.9236, 43.5883],
    '甘肃': [103.5901, 36.3043],
    '北京': [116.4551, 40.2539],
    '江苏': [118.8062, 31.9208],
    '广西': [108.479, 23.1152],
    '江西': [116.0046, 28.6633],
    '安徽': [117.29, 32.0581],
    '内蒙古': [111.4124, 40.4901],
    '黑龙江': [127.9688, 45.368],
    '天津': [117.4219, 39.4189],
    '山西': [112.3352, 37.9413],
    '广东': [113.5107, 23.2196],
    '四川': [103.9526, 30.7617],
    '西藏': [91.1865, 30.1465],
    '云南': [102.9199, 25.4663],
    '浙江': [119.5313, 29.8773],
    '湖北': [114.3896, 30.6628],
    '辽宁': [123.1238, 42.1216],
    '山东': [117.1582, 36.8701],
    '海南': [110.3893, 19.8516],
    '河北': [114.4995, 38.1006],
    '福建': [119.4543, 25.9222],
    '青海': [101.4038, 36.8207],
    '陕西': [109.1162, 34.2004],
    '贵州': [106.6992, 26.7682],
    '河南': [113.4668, 34.6234],
    '重庆': [107.7539, 30.1904],
    '宁夏': [106.3586, 38.1775],
    '吉林': [125.8154, 44.2584],
    '湖南': [113.0823, 28.2568]
};

var visualColor = ['#00A364', '#FDB737', '#FF373A'];  //线的颜色

var insset = '';
var comset = '';
//工业
var bigInsPro = [];
function insMapData(ins) {
    //ins  = ins[0]
    clearInterval(comset);
    var bigIns = [];
    for(var i = 0;i<ins.length;i++){
        var mapLine = ins[i].mapLine;
        var pronum1 = Math.round(ins[i].pronum1);
        bigIns.push(getMapLineDataModel(mapLine))
        bigInsPro.push(pronum1)
    }
    var bigI = 0;
    var bigLength = bigIns.length;
    // var myChart = echarts.init(document.getElementById('mainMap'));
    // myChart.setOption(option, true);
    insset = setInterval(function oneLoad() {
        insMap(bigIns[bigI],bigInsPro[bigI]);
        var myChart = echarts.init(document.getElementById('mainMap'));
        myChart.setOption(option, true);
        bigI++;
        if(bigI == bigLength){
            bigI = 0;
            //clearInterval(mxset)
        }
        return oneLoad;}(), 5000);
}
//商业
var bigInsNumBs = [];
function comMapData(com) {
    clearInterval(insset);
    //$("#mainMap").hide();
    //$("#mainMap2").show();
    var big = [];
    for(var i = 0;i<com.length;i++){
        var mapLine = com[i].mapLine;
        var outNumBs = Math.round(com[i].outNumBs);
        big.push(getMapLineDataModel(mapLine))
        bigInsNumBs.push(outNumBs)
    }
    var bigICom = 0;
    var bigLength = big.length;
    // comMap(big[0]);
    // var myChart2 = echarts.init(document.getElementById('mainMap2'));
    // myChart2.setOption(option2, true);
    comset = setInterval(function oneLoad() {
        comMap(big[bigICom],bigInsNumBs[bigICom]);
        //document.getElementById('mainMap2').style.width=$(".map_div_width").css('width');
        var myChart2 = echarts.init(document.getElementById('mainMap2'));
        myChart2.setOption(option2, true);
        bigICom++;
        if(bigICom == bigLength){
            bigICom = 0;
            //clearInterval(mxset)
        }
        return oneLoad;}(), 5000);
}
function getMapLineDataModel(mapLine) {
    var map_name = [];
    for(var i = 0 ;i< mapLine.length ;i++ ){
        map_name[i] = new Object();
        map_name[i].name = mapLine[i].mapsdata.name;
    }
    var map_value = [];
    for(var i = 0 ;i< mapLine.length ;i++ ){
        map_value[i] = new Object();
        map_value[i].name = mapLine[i].mapdata.name;
        map_value[i].value = mapLine[i].mapdata.value;
    }
    var map = new Array();
    for(var a = 0;a< mapLine.length ;a++){
        map[a] = new Array();
        map[a][0] = map_name[a];
        map[a][1] = map_value[a];
    }
    return map;
}

//工业地图 ， 向外汇聚
function insMap(AhData,bigInsPro) {
    if(AhData.length == 0){
        return
    }
    /*var AhData = [
        [{name:'安徽'}, {name:'辽宁',value:90}],
        [{name:'安徽'}, {name:'河北',value:10}],
        [{name:'安徽'}, {name:'河南',value:15}],
        [{name:'安徽'}, {name:'甘肃',value:20}],
        [{name:'安徽'}, {name:'陕西',value:90}],
        [{name:'安徽'}, {name:'四川',value:60}],
        [{name:'安徽'}, {name:'广东',value:20}],
        [{name:'安徽'}, {name:'贵州',value:20}],
        [{name:'安徽'}, {name:'福建',value:10}]
    ];*/
    var zongXiang = 0;
    for(var i = 0;i < AhData.length;i++){
        zongXiang = zongXiang + Number(AhData[i][1].value)
    }
    // var planePath = 'path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z';
    var convertData = function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var dataItem = data[i];
            var fromCoord = geoCoordMap[dataItem[0].name];
            var toCoord = geoCoordMap[dataItem[1].name];
            if (fromCoord && toCoord) {
                res.push({
                    fromName: dataItem[0].name,
                    toName: dataItem[1].name,
                    value: dataItem[1].value,
                    coords: [fromCoord, toCoord]
                });
            }
        }
        return res;
    };

    var gzname = [AhData[0][0].name];  //["安徽"];
    var gzvalue = [zongXiang]; //title显示总箱
    var gzs = [];
    for (var i = 0; i < gzname.length; i++) {
        gzs.push({
            name: gzname[i],
            value: gzvalue[i]
        })
    }
    var gdGeoCoordMap = {
        // '安徽': [117.29, 32.0581],
        [gzname] : geoCoordMap[gzname]
    };
    var convertDataTitle = function(data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var geoCoord = gdGeoCoordMap[data[i].name];
            if (geoCoord) {
                res.push({
                    name: data[i].name,
                    value: geoCoord.concat(data[i].value)
                });
            }
        }
        return res;
    };

    var series = [];
    [[gzname, AhData]].forEach(function (item, i) {
        series.push(
            {
                name: item[0],
                type: 'lines',
                zlevel: 1,
                effect: {
                    show: true,
                    period: 4,
                    trailLength: 0.7,
                    color: '#fff',
                    symbolSize: 11,
                    opacity:1
                },
                lineStyle: {
                    normal: {
                        color: visualColor[i],
                        width: 2,
                        curveness: 0.2
                    }
                },
                data: convertData(item[1])
            },
            {
                name: item[0],
                type: 'lines',
                zlevel: 2,
                rippleEffect: { //涟漪特效
                    period: 4, //动画时间，值越小速度越快
                    brushType: 'stroke', //波纹绘制方式 stroke, fill
                    scale: 4 //波纹圆环最大限制，值越大波纹越大
                },
                symbol: 'circle',
                //symbolSize: 10,
                // effect: {
                //     show: true,
                //     period: 6,
                //     trailLength: 0,
                //     // symbol: planePath,  //飞机
                //     // symbol: "arrow",  //箭头
                //     symbolSize: 8
                // },
                //设置线和小飞机的颜色，其中value值根据传入的参数params来定
                lineStyle: {
                    normal: {
                        color: function(params){
                            let num1 = params.dataIndex
                            let num=AhData[num1][1].value;
                            //设置线的
                            if(num > 600){
                                return visualColor[2];
                            }else if(num >300){
                                return visualColor[1];
                            } else{
                                return visualColor[0];
                            }
                        },
                        // width: 2,
                        // opacity: 1,
                        curveness: 0.2
                    }
                },
                data: convertData(item[1])
            },
            {
                name: item[0],
                type: 'scatter',
                coordinateSystem: 'geo',
                zlevel: 2,
                rippleEffect: { //涟漪特效
                    period: 4, //动画时间，值越小速度越快
                    brushType: 'stroke', //波纹绘制方式 stroke, fill
                    scale: 4 //波纹圆环最大限制，值越大波纹越大
                },
                symbol: 'circle',
                // rippleEffect: {
                //     brushType: 'stroke'
                // },
                // label: {
                //     normal: {
                //         show: true,
                //         position: 'right',
                //         formatter: '{b}'
                //     }
                // },
                symbolSize: function (val) {
                    // return val[2] / 8;
                    return 11;
                },
                //设置终点颜色
                itemStyle: {
                    normal: {
                        color: function(params){
                            let num1 = params.dataIndex
                            let num=AhData[num1][1].value;
                            if(num > 600){
                                return visualColor[2];
                            }else if(num >300){
                                return visualColor[1];
                            } else{
                                return visualColor[0];
                            }
                        }
                    }
                },
                /*data: item[1].map(function (dataItem) {
                    return {
                        name: dataItem[1].name,
                        value: geoCoordMap[dataItem[1].name].concat([dataItem[1].value])
                    };
                })*/
                label: {
                    normal: {
                        show: true,
                        formatter: function(params) {
                            //$("#map_xuanfu_num").html(params.name+'&nbsp;'+gzvalue[params.dataIndex])
                            //return '{fline|' + params.name + '}{tline|' + gzvalue[params.dataIndex] + '}';
                            let arr = ["发货省："+params.name, "发货量：" + gzvalue[params.dataIndex] , "产   量：" + bigInsPro];
                            return arr.join("\n")
                        },
                        position: [20, 20, 0, 0],
                        padding: [0, 0],
                        borderRadius: [0, 3, 3, 0],
                        lineHeight: 45,

                        color: '#fff',
                        backgroundColor: "#1a3961",
                        borderColor: '#aee9fb',
                        borderWidth: 1,
                        padding: [5, 10, 5, 10],
                        borderRadius: 6,
                        fontSize: 28,

                        rich: {
                            fline: {
                                positionX:50,
                                padding: [0, 5, 0, 10],
                                color: '#FF3C39',
                                fontSize: 48,
                            },
                            tline: {
                                positionX:60,
                                padding: [0, 10, 0, 5],
                                color: '#FF3C39',
                                fontSize: 48,
                            },
                        }
                    },
                    emphasis: {
                        show: true
                    }
                },
                z: 999,
                data: convertDataTitle(gzs)
            }
        );
    });
    option = {
        layoutCenter: ['40%', '50%'],//距离左右，上下距离的百分比
        layoutSize:'120%',//map百分比大小
        title : {
            text: '单位：箱',
            // subtext: '单位(箱)',
            top:100,
            right: 200,
            textStyle : {
                fontSize:28,
                color: '#8BC9E9'
            }
        },
        visualMap: {
            type: 'continuous',
            orient: 'horizontal',
            itemWidth: 23,
            itemHeight: 242,
            text: ['高','低'],
            showLabel: true,
            seriesIndex: [0],
            max: 1000,
            min: 0,
            inRange: {
                color: visualColor
            },
            textStyle: {
                color: '#40a1db',
                fontSize: 23
            },
            bottom: 160,
            left: 150
        },
        geo: {
            map: 'china',
            label: {
                emphasis: {
                    show: true,
                    color: '#ffffff'
                }
            },
            roam: false,
            itemStyle: {
                normal: {
                    areaColor: {
                        type: 'linear',
                        x: 0,
                        y: 1,
                        x2: 0.7,
                        y2: 0,
                        colorStops: [{
                            offset: 0,
                            color: '#45b0e7' // 0% 处的颜色

                        }, {
                            offset: 1,
                            color: '#78cdf0' // 100% 处的颜色
                        }],
                        globalCoord: false // 缺省为 false
                    },
                    // areaColor: '#77cdf0',  //地图背景
                    borderColor: '#0072c2' , //边框
                    borderWidth:1,
                },
                emphasis: {
                    areaColor: '#4fb6ea'  // 悬浮背景
                }
            }
        },
        series: series
    };
}



//商业地图 ，向内汇聚
function comMap(chinaDatas,bigInsNumBs) {
    if(chinaDatas.length == 0){
        return
    }
    /*var chinaDatas = [
        [{
            name: '内蒙古',
            value: 50
        }],	[{
            name: '吉林',
            value: 80
        }],	[{
            name: '上海',
            value: 20
        }]
    ];*/
    var zongXiang = 0;
    for(var i = 0;i < chinaDatas.length;i++){
        zongXiang = zongXiang + Number(chinaDatas[i][1].value)
    }
    var gzname2 = [chinaDatas[0][1].name];
    var gzvalue2 = [zongXiang];
    var gzs2 = [];
    for (var i = 0; i < gzname2.length; i++) {
        gzs2.push({
            name: gzname2[i],
            value: gzvalue2[i]
        })
    }
    var gdGeoCoordMap2 = {
        // '北京': [116.4551, 40.2539],
        [gzname2] : geoCoordMap[gzname2]
    };
    var convertData2 = function(data) {
        //console.log(data)
        var res = [];
        for(var i = 0; i < data.length; i++) {
            var dataItem = data[i];
            var fromCoord = geoCoordMap[dataItem[0].name];
            //var toCoord = [116.4551,40.2539];
            if(fromCoord && Object.values(gdGeoCoordMap2)[0]) {
                res.push([{
                    coord: fromCoord,
                    value: dataItem[1].value
                }, {
                    coord: Object.values(gdGeoCoordMap2)[0],
                }]);
            }
        }
        return res;
    };
    var convertDataTitle2 = function(data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var geoCoord = gdGeoCoordMap2[data[i].name];
            if (geoCoord) {
                res.push({
                    name: data[i].name,
                    value: geoCoord.concat(data[i].value)
                });
            }
        }
        return res;
    };

    var series2 = [];
    [[gzname2, chinaDatas]].forEach(function(item, i) {
        series2.push({
                type: 'lines',
                zlevel: 1,
                effect: {
                    show: true,
                    period: 4,
                    trailLength: 0.7,
                    color: '#fff',
                    symbolSize: 11
                },
                lineStyle: {
                    normal: {
                        color:visualColor[i],
                        /*color: function(params){
                            let num1 = params.dataIndex
                            // let num=chinaDatas[num1][0].value;
                            let num=chinaDatas[num1][1].value;
                            // if(num > 60){
                            //     return visualColor[2];
                            // }else if(num >30){
                            //     return visualColor[1];
                            // } else{
                            //     return visualColor[0];
                            // }
                        },*/
                        width: 2,
                        //opacity: 0.6,
                        curveness: 0.2
                    }
                },
                data: convertData2(item[1])
            }, {
                type: 'effectScatter',
                coordinateSystem: 'geo',
                zlevel: 2,
                rippleEffect: { //涟漪特效
                    period: 4, //动画时间，值越小速度越快
                    brushType: 'stroke', //波纹绘制方式 stroke, fill
                    scale: 4 //波纹圆环最大限制，值越大波纹越大
                },

                symbol: 'circle',
                symbolSize: function (val) {
                    // return val[2] / 8;
                    return 11;
                },
                //设置终点颜色
                itemStyle: {
                    normal: {
                        color: function(params){
                            let num1 = params.dataIndex
                            let num=chinaDatas[num1][1].value;
                            if(num > 600){
                                return visualColor[2];
                            }else if(num >300){
                                return visualColor[1];
                            } else{
                                return visualColor[0];
                            }
                        }
                    }
                },
                data: item[1].map(function(dataItem) {
                    return {
                        name: dataItem[0].name,
                        value: geoCoordMap[dataItem[0].name].concat([dataItem[0].value])
                    };
                }),
            },
            //被攻击点
            {
                type: 'scatter',
                coordinateSystem: 'geo',
                zlevel: 2,
                rippleEffect: {
                    period: 4,
                    brushType: 'stroke',
                    scale: 4
                },
                //
                itemStyle: {
                    normal: {
                        // color: function(params){
                        //     let num1 = params.dataIndex
                        //     let num=chinaDatas[num1][0].value;
                        //     if(num > 60){
                        //         return visualColor[2];
                        //     }else if(num >30){
                        //         return visualColor[1];
                        //     } else{
                        //         return visualColor[0];
                        //     }
                        // }
                    }
                },
                // symbol: 'pin', //启动位置图标
                // symbolSize: 50,
                /*data: [{
                    name: item[0],
                    value: geoCoordMap[item[0]].concat([10]),
                }],*/
                label: {
                    normal: {
                        show: true,
                        formatter: function(params) {
                            //$("#map_xuanfu_num").html(params.name+gzvalue2[params.dataIndex])
                            // return '{fline|' + params.name + '}{tline|' + gzvalue2[params.dataIndex] + '}';
                            let arr = ["到货省："+params.name, "到货量：" + gzvalue2[params.dataIndex], "销售量：" + bigInsNumBs];
                            return arr.join("\n")
                        },
                        position: [20, 20, 0, 0],
                        padding: [0, 0],
                        borderRadius: [0, 3, 3, 0],
                        lineHeight: 45,

                        color: '#fff',
                        backgroundColor: "#1a3961",
                        borderColor: '#aee9fb',
                        borderWidth: 1,
                        padding: [5, 5, 5, 5],
                        borderRadius: 6,
                        fontSize: 28,

                        rich: {
                            fline: {
                                padding: [0, 5, 0, 10],
                                color: '#FF3C39',
                                fontSize: 48,
                            },
                            tline: {
                                padding: [0, 10, 0, 5],
                                color: '#FF3C39',
                                fontSize: 48,
                            },
                        }
                    },
                    emphasis: {
                        show: true
                    }
                },
                z: 999,
                data: convertDataTitle2(gzs2)
            }
        );
    });

    option2 = {
        layoutCenter: ['40%', '50%'],//距离左右，上下距离的百分比
        layoutSize:'120%',//map百分比大小
        animation: false,
        title : {
            text: '单位：箱',
            // subtext: '单位(箱)',
            top:100,
            right: 200,
            textStyle : {
                fontSize:28,
                color: '#8BC9E9'
            }
        },
        visualMap: {
            type: 'continuous',
            orient: 'horizontal',
            itemWidth: 23,
            itemHeight: 242,
            text: ['高','低'],
            showLabel: true,
            seriesIndex: [0],
            max: 1000,
            min: 0,
            inRange: {
                color: visualColor
            },
            textStyle: {
                color: '#40a1db',
                fontSize: 23
            },
            bottom: 160,
            left: 150
        },
        geo: {
            map: 'china',
            label: {
                emphasis: {
                    show: false, //鼠标地图显示地面
                    color: '#ffffff'
                }
            },
            roam: false,
            itemStyle: {
                normal: {
                    areaColor: {
                        type: 'linear',
                        x: 0,
                        y: 1,
                        x2: 0.7,
                        y2: 0,
                        colorStops: [{
                            offset: 0,
                            color: '#45b0e7' // 0% 处的颜色

                        }, {
                            offset: 1,
                            color: '#78cdf0' // 100% 处的颜色
                        }],
                        globalCoord: false // 缺省为 false
                    },
                    // areaColor: '#77cdf0',  //地图背景
                    borderColor: '#0072c2' , //边框
                    borderWidth:1,
                },
                emphasis: {
                    areaColor: '#4fb6ea'  // 悬浮背景
                }
            }
        },
        series: series2
    };
}

