function getAccuracy(e) {
        var t = ""
          , n = function(e) {
            return t = i(a(o(r(e), 8 * e.length))),
            t.toLowerCase()
        };
        function i(e) {
            for (var t, n = "0123456789ABCDEF", i = "", r = 0; r < e.length; r++)
                t = e.charCodeAt(r),
                i += n.charAt(t >>> 4 & 15) + n.charAt(15 & t);
            return i
        }
        function r(e) {
            for (var t = Array(e.length >> 2), n = 0; n < t.length; n++)
                t[n] = 0;
            for (n = 0; n < 8 * e.length; n += 8)
                t[n >> 5] |= (255 & e.charCodeAt(n / 8)) << n % 32;
            return t
        }
        function a(e) {
            for (var t = "", n = 0; n < 32 * e.length; n += 8)
                t += String.fromCharCode(e[n >> 5] >>> n % 32 & 255);
            return t
        }
        function o(e, t) {
            e[t >> 5] |= 128 << t % 32,
            e[14 + (t + 64 >>> 9 << 4)] = t;
            for (var n = 1732584193, i = -271733879, r = -1732584194, a = 271733878, o = 0; o < e.length; o += 16) {
                var s = n
                  , f = i
                  , m = r
                  , p = a;
                i = d(i = d(i = d(i = d(i = u(i = u(i = u(i = u(i = c(i = c(i = c(i = c(i = l(i = l(i = l(i = l(i, r = l(r, a = l(a, n = l(n, i, r, a, e[o + 0], 7, -680876936), i, r, e[o + 1], 12, -389564586), n, i, e[o + 2], 17, 606105819), a, n, e[o + 3], 22, -1044525330), r = l(r, a = l(a, n = l(n, i, r, a, e[o + 4], 7, -176418897), i, r, e[o + 5], 12, 1200080426), n, i, e[o + 6], 17, -1473231341), a, n, e[o + 7], 22, -45705983), r = l(r, a = l(a, n = l(n, i, r, a, e[o + 8], 7, 1770035416), i, r, e[o + 9], 12, -1958414417), n, i, e[o + 10], 17, -42063), a, n, e[o + 11], 22, -1990404162), r = l(r, a = l(a, n = l(n, i, r, a, e[o + 12], 7, 1804603682), i, r, e[o + 13], 12, -40341101), n, i, e[o + 14], 17, -1502002290), a, n, e[o + 15], 22, 1236535329), r = c(r, a = c(a, n = c(n, i, r, a, e[o + 1], 5, -165796510), i, r, e[o + 6], 9, -1069501632), n, i, e[o + 11], 14, 643717713), a, n, e[o + 0], 20, -373897302), r = c(r, a = c(a, n = c(n, i, r, a, e[o + 5], 5, -701558691), i, r, e[o + 10], 9, 38016083), n, i, e[o + 15], 14, -660478335), a, n, e[o + 4], 20, -405537848), r = c(r, a = c(a, n = c(n, i, r, a, e[o + 9], 5, 568446438), i, r, e[o + 14], 9, -1019803690), n, i, e[o + 3], 14, -187363961), a, n, e[o + 8], 20, 1163531501), r = c(r, a = c(a, n = c(n, i, r, a, e[o + 13], 5, -1444681467), i, r, e[o + 2], 9, -51403784), n, i, e[o + 7], 14, 1735328473), a, n, e[o + 12], 20, -1926607734), r = u(r, a = u(a, n = u(n, i, r, a, e[o + 5], 4, -378558), i, r, e[o + 8], 11, -2022574463), n, i, e[o + 11], 16, 1839030562), a, n, e[o + 14], 23, -35309556), r = u(r, a = u(a, n = u(n, i, r, a, e[o + 1], 4, -1530992060), i, r, e[o + 4], 11, 1272893353), n, i, e[o + 7], 16, -155497632), a, n, e[o + 10], 23, -1094730640), r = u(r, a = u(a, n = u(n, i, r, a, e[o + 13], 4, 681279174), i, r, e[o + 0], 11, -358537222), n, i, e[o + 3], 16, -722521979), a, n, e[o + 6], 23, 76029189), r = u(r, a = u(a, n = u(n, i, r, a, e[o + 9], 4, -640364487), i, r, e[o + 12], 11, -421815835), n, i, e[o + 15], 16, 530742520), a, n, e[o + 2], 23, -995338651), r = d(r, a = d(a, n = d(n, i, r, a, e[o + 0], 6, -198630844), i, r, e[o + 7], 10, 1126891415), n, i, e[o + 14], 15, -1416354905), a, n, e[o + 5], 21, -57434055), r = d(r, a = d(a, n = d(n, i, r, a, e[o + 12], 6, 1700485571), i, r, e[o + 3], 10, -1894986606), n, i, e[o + 10], 15, -1051523), a, n, e[o + 1], 21, -2054922799), r = d(r, a = d(a, n = d(n, i, r, a, e[o + 8], 6, 1873313359), i, r, e[o + 15], 10, -30611744), n, i, e[o + 6], 15, -1560198380), a, n, e[o + 13], 21, 1309151649), r = d(r, a = d(a, n = d(n, i, r, a, e[o + 4], 6, -145523070), i, r, e[o + 11], 10, -1120210379), n, i, e[o + 2], 15, 718787259), a, n, e[o + 9], 21, -343485551),
                n = h(n, s),
                i = h(i, f),
                r = h(r, m),
                a = h(a, p)
            }
            return Array(n, i, r, a)
        }
        function s(e, t, n, i, r, a) {
            return h(f(h(h(t, e), h(i, a)), r), n)
        }
        function l(e, t, n, i, r, a, o) {
            return s(t & n | ~t & i, e, t, r, a, o)
        }
        function c(e, t, n, i, r, a, o) {
            return s(t & i | n & ~i, e, t, r, a, o)
        }
        function u(e, t, n, i, r, a, o) {
            return s(t ^ n ^ i, e, t, r, a, o)
        }
        function d(e, t, n, i, r, a, o) {
            return s(n ^ (t | ~i), e, t, r, a, o)
        }
        function h(e, t) {
            var n = (65535 & e) + (65535 & t);
            return (e >> 16) + (t >> 16) + (n >> 16) << 16 | 65535 & n
        }
        function f(e, t) {
            return e << t | e >>> 32 - t
        }
        var m = n(e)
          , p = m.substring(12, 16) + m.substring(8, 12) + m.substring(20, 24) + m.substring(4, 8) + m.substring(16, 20) + m.substring(24) + m.substring(0, 4);
        return p
}