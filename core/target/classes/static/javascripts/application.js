window.helper = (function () {
    let formatMoney = function (n, c, d, t) {
        c = isNaN((c = Math.abs(c))) ? 2 : c;
        d = d == undefined ? "." : d;
        t = t == undefined ? "," : t;
        let s = n < 0 ? "-" : "",
            i = String(parseInt((n = Math.abs(Number(n) || 0).toFixed(c)))),
            j = i.length > 3 ? i.length % 3 : 0;

        return (
            s +
            (j ? i.substr(0, j) + t : "") +
            i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) +
            (c ? d +
                Math.abs(n - i)
                    .toFixed(c)
                    .slice(2)
                : "")
        );
    }

    let countdownTimer = 120;

    return {
        isWechat: function () {
            let ua = window.navigator.userAgent.toLowerCase();
            if (ua.match(/MicroMessenger/i) == "micromessenger") {
                return true;
            } else {
                return false;
            }
        },
        csrfToken: function () {
            return $('meta[name="csrf-token"]').attr("content");
        },
        numberToCurrency: function (n) {
            return "￥ " + formatMoney(Number(n));
        },
        startProgress: function () {
            $(".upload-progress").show();
        },
        endProgress: function () {
            $(".upload-progress").fadeOut(100);
        },
        displayDate: function (d) {
            return d.getFullYear() + "-" + ("0" + (d.getMonth() + 1)).slice(-2) + "-" + ("0" + d.getDate()).slice(-2);
        },
        ajaxModal: function (url, params) {
            $("#ajaxModalContent").html($("#ajaxModalLoadingTemplate").html());
            $("#ajaxModal").modal("show");
            params = params || {};
            $.get(url, params, function (data) {
                $("#ajaxModalContent").html(data);
            });
        },
        notice: function (msg) {
            $("#sysNotice").html('<div class="alert alert-info" role="alert"><i class="fa fa-check-circle"></i> ' + msg + "</div>");
            $("#sysNotice").show().delay(5000).fadeOut();
        },
        countdown: function ($btn) {
            if (countdownTimer <= 0) {
                countdownTimer = 120;
                $btn.html("获取验证码").removeAttr("disabled");
                return;
            }
            $btn.html("已发送（" + countdownTimer + "）").prop("disabled", true);
            setTimeout(function () {
                helper.countdown($btn);
            }, 1000);
            countdownTimer--;
        }
    };
});

window.paginate = {
    toPage: function (t) {
        let page = Number($(t).attr("data"));
        let url = new URL(window.location.href);
        url.searchParams.delete("page");
        url.searchParams.append("page", page);
        window.location.href = url;
    }
}

// 初始化
$(function () {
    $.datetimepicker.setLocale("ch");
    $(".date-picker").datetimepicker({
        timepicker: false,
        format: "Y-m-d",
    });
    $(".datetime-picker").datetimepicker({
        format: "Y-m-d H:i:00",
    });
    $(".time-picker").datetimepicker({
        format: "H:i:00",
    });
    $(".datetime-picker-end").datetimepicker({
        format: "Y-m-d H:i:59",
        allowTimes: [
            "00:59",
            "01:59",
            "02:59",
            "03:59",
            "04:59",
            "05:59",
            "06:59",
            "07:59",
            "08:59",
            "09:59",
            "10:59",
            "11:59",
            "12:59",
            "13:59",
            "14:59",
            "15:59",
            "16:59",
            "17:59",
            "18:59",
            "19:59",
            "20:59",
            "21:59",
            "22:59",
            "23:59",
        ],
    });
    $(".popover-container").popover();
    $(".tooltip-container").tooltip();
    $('[data-toggle="popover"]').popover();
    // wysiwyg.init();
    $("#sysNotice").show().delay(5000).fadeOut();
    $("a[data-method]").click(function () {
        try {
            let $t = $(this);
            if ($t.attr("data-confirm")) {
                if (!confirm($t.attr("data-confirm"))) {
                    return false;
                }
            }
            let $f = $('<form method="' + $t.attr("data-method") + '" action="' + $t.attr("href") + '"></form>');
            $f.appendTo($("body"));
            $f[0].submit();
        } catch (e) {
            console.log(e);
        }
        return false;
    });
});
