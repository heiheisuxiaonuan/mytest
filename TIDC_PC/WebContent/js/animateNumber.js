// ��������ʱ��
var renderSpeed = 5000;

var newSpeed;
// ǧλѭ������
var times;
// ǧλ���չʾ�����֣���ȥ��λ��������
var rem = 0;
// ��λ����
var tenThousand;

// ԭ��������
function changeNum(s, g, newvalue) {

    newSpeed = 0;
    // ǧλѭ������
    times = 0;
    // ǧλ���չʾ�����֣���ȥ��λ��������
    rem = 0;
    // ��λ����
    tenThousand = 0;
    //oldValue(s,g)
    initNum(newvalue, oldValue(s, g))
    tenThousandChange(s);
    thousandChange(g);
    setTimeout(function () {
        $("#" + s).css("color", "#C2F5FF");
        $("#" + g).css("color", "#C2F5FF");
    }, renderSpeed+500)
}

function oldValue(s, g) {
    //times = 0
    var sv = $("#" + s).html();
    var gv = $("#" + g).html();
    lll = sv + gv;
    return lll;
    //var oldProduction = 81234;
    //initNum(newvalue,ov);
    // // ���÷���

}


function initNum(newProduction, oldProduction) {
    tenThousand = parseInt(newProduction / 10000);
    // ����ȡ����
    times = parseInt(Math.abs(newProduction - oldProduction) / 10000) == 0 ? 0 : parseInt(Math.abs(newProduction - oldProduction) / 10000);
    rem = newProduction % 10000;
}

// tenThousandChange(s);
// thousandChange(g);

// ��λ��Ч
function tenThousandChange(target) {
    show_num(target, tenThousand, renderSpeed, false);
};

// ǧλ��Ч
function thousandChange(target) {
    if (times == 0) {
        newSpeed = renderSpeed;
    } else {
        newSpeed = Math.ceil(renderSpeed / (times+1));
    }
    for (var i = 0; i < times; i++) {
        show_num(target, 9999, newSpeed, true);
    }
    if (rem != 0) {
        show_num(target, rem, newSpeed, false);
    }else{
        setTimeout(function () {
            console.log(target);
            $("#" + target).html(repair(0,4));
        },renderSpeed-200)
    }
};

// ���ֶ�Ч
function show_num(target, n, r, b) {
    var objMain = $("#" + target);

    var old = parseInt(objMain.html() == '' ? 0 : objMain.html());
    if (old == 0) {
        objMain.html(n);
    }
    if (b) {
        objMain.html(0);
    }
    if (old == 9999 && b) {
        objMain.html(0);
        //return;
    }
    if (old == n) {
        $("#" + target).css("color", "#C2F5FF");
        return;
    } else {
        $("#" + target).css("color", '#89F16B');
    }
    objMain.prop('number', old).animateNumber({
        number: n
    }, r, function () {
        $(".buwei").html(repair($(".buwei").html(),4));
    });
}

function repair(num, n) {
    var len = num.toString().length;
    while (len < n) {
        num = "0" + num;
        len++;
    }
    return num;
}
