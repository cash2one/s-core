(function() {
    var G = window,K = document,A = navigator,C = A.userAgent,D = location.href,I = A.mimeTypes,B = 9,F = "http://kiks.%s/system/fc06.html",L = "http://kiks.yandex.ru/system/fc06.swf",H = "application/x-shockwave-flash";
    G.onerror = function() {
        return true
    };
    G.ya_fc = function() {
        var R = (C && (C.indexOf("MSIE") >= 0) && (C.indexOf("Win") != -1)) ? 1 : 0,O = (I && I[H]) ? I[H].enabledPlugin : 0,Q = false,N = 0;
        if (O) {
            N = O.description.split("Shockwave Flash ")[1];
            Q = (parseInt(N) >= B)
        } else {
            if (R) {
                Q = false;
                try {
                    Q = new ActiveXObject("ShockwaveFlash.ShockwaveFlash." + B)
                } catch(P) {
                }
            }
        }
        if (Q) {
            K.write('<object type="' + H + '" data="' + L + '" width="1" height="1" id="ya_fc"><param name="movie" value="' + L + '" /><param name="quality" value="high" /><param name="wmode" value="window" /><param name="bgcolor" value="#ffffff" /><param name="allowScriptAccess" value="always" /></object>')
        } else {
        }
    };
    G.requestData = function() {
        var N = K.getElementById("ya_fc");
        try {
            N.setLocation(M(), J())
        } catch(O) {
        }
    };
    G.setCookie = function(O) {
        var N = new Date();
        N.setTime(N.getTime() + 315360000000);
        K.cookie = "fuid01=" + O + ";expires=" + N.toGMTString() + ";path=/;domain=" + M()
    };
    G.getIFrame = function(P) {
        var Q = F.replace("%s", P),O = K.createElement("iframe"),N = K.getElementsByTagName("body");
        O.src = Q;
        O.style.cssText = "width:40px;height:40px;overflow:hidden;position:absolute;left:-40px;top:0;border:0;";
        O.frameBorder = 0;
        N && N[0].appendChild(O)
    };
    function E(P, O, Q) {
        var N = P.match(O);
        return N && N[Q || 1] || ""
    }

    function J() {
        return E(K.cookie, /fuid01=([^;]+)/)
    }

    function M() {
        var N = E(D, /\/\/([^\/:]+)/);
        return N && E(N, /(^|\.)([^\.]+\.[^\.]+)$/, 2)
    }
})();
ya_fc()