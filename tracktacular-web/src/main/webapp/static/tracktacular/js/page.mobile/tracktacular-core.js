(function(e){if(String.prototype.trim===e)String.prototype.trim=function(){return this.replace(/^\s+|\s+$/g,"")};if(Array.prototype.reduce===e)Array.prototype.reduce=function(t){if(this===void 0||this===null)throw new TypeError;var n=Object(this),r=n.length>>>0,i=0,s;if(typeof t!="function")throw new TypeError;if(r==0&&arguments.length==1)throw new TypeError;if(arguments.length>=2)s=arguments[1];else do{if(i in n){s=n[i++];break}if(++i>=r)throw new TypeError}while(true);while(i<r){if(i in n)s=t.call(e,s,n[i],i,n);i++}return s}})();var Zepto=function(){function O(e){return e==null?String(e):T[N.call(e)]||"object"}function M(e){return O(e)=="function"}function _(e){return e!=null&&e==e.window}function D(e){return e!=null&&e.nodeType==e.DOCUMENT_NODE}function P(e){return O(e)=="object"}function H(e){return P(e)&&!_(e)&&e.__proto__==Object.prototype}function B(e){return e instanceof Array}function j(e){return typeof e.length=="number"}function F(e){return o.call(e,function(e){return e!=null})}function I(e){return e.length>0?n.fn.concat.apply([],e):e}function q(e){return e.replace(/::/g,"/").replace(/([A-Z]+)([A-Z][a-z])/g,"$1_$2").replace(/([a-z\d])([A-Z])/g,"$1_$2").replace(/_/g,"-").toLowerCase()}function R(e){return e in f?f[e]:f[e]=new RegExp("(^|\\s)"+e+"(\\s|$)")}function U(e,t){return typeof t=="number"&&!c[q(e)]?t+"px":t}function z(e){var t,n;if(!a[e]){t=u.createElement(e);u.body.appendChild(t);n=l(t,"").getPropertyValue("display");t.parentNode.removeChild(t);n=="none"&&(n="block");a[e]=n}return a[e]}function W(e){return"children"in e?s.call(e.children):n.map(e.childNodes,function(e){if(e.nodeType==1)return e})}function X(n,r,i){for(t in r)if(i&&(H(r[t])||B(r[t]))){if(H(r[t])&&!H(n[t]))n[t]={};if(B(r[t])&&!B(n[t]))n[t]=[];X(n[t],r[t],i)}else if(r[t]!==e)n[t]=r[t]}function V(t,r){return r===e?n(t):n(t).filter(r)}function $(e,t,n,r){return M(t)?t.call(e,n,r):t}function J(e,t,n){n==null?e.removeAttribute(t):e.setAttribute(t,n)}function K(t,n){var r=t.className,i=r&&r.baseVal!==e;if(n===e)return i?r.baseVal:r;i?r.baseVal=n:t.className=n}function Q(e){var t;try{return e?e=="true"||(e=="false"?false:e=="null"?null:!isNaN(t=Number(e))?t:/^[\[\{]/.test(e)?n.parseJSON(e):e):e}catch(r){return e}}function G(e,t){t(e);for(var n in e.childNodes)G(e.childNodes[n],t)}var e,t,n,r,i=[],s=i.slice,o=i.filter,u=window.document,a={},f={},l=u.defaultView.getComputedStyle,c={"column-count":1,columns:1,"font-weight":1,"line-height":1,opacity:1,"z-index":1,zoom:1},h=/^\s*<(\w+|!)[^>]*>/,p=/<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/ig,d=/^(?:body|html)$/i,v=["val","css","html","text","data","width","height","offset"],m=["after","prepend","before","append"],g=u.createElement("table"),y=u.createElement("tr"),b={tr:u.createElement("tbody"),tbody:g,thead:g,tfoot:g,td:y,th:y,"*":u.createElement("div")},w=/complete|loaded|interactive/,E=/^\.([\w-]+)$/,S=/^#([\w-]*)$/,x=/^[\w-]+$/,T={},N=T.toString,C={},k,L,A=u.createElement("div");C.matches=function(e,t){if(!e||e.nodeType!==1)return false;var n=e.webkitMatchesSelector||e.mozMatchesSelector||e.oMatchesSelector||e.matchesSelector;if(n)return n.call(e,t);var r,i=e.parentNode,s=!i;if(s)(i=A).appendChild(e);r=~C.qsa(i,t).indexOf(e);s&&A.removeChild(e);return r};k=function(e){return e.replace(/-+(.)?/g,function(e,t){return t?t.toUpperCase():""})};L=function(e){return o.call(e,function(t,n){return e.indexOf(t)==n})};C.fragment=function(t,r,i){if(t.replace)t=t.replace(p,"<$1></$2>");if(r===e)r=h.test(t)&&RegExp.$1;if(!(r in b))r="*";var o,u,a=b[r];a.innerHTML=""+t;u=n.each(s.call(a.childNodes),function(){a.removeChild(this)});if(H(i)){o=n(u);n.each(i,function(e,t){if(v.indexOf(e)>-1)o[e](t);else o.attr(e,t)})}return u};C.Z=function(e,t){e=e||[];e.__proto__=n.fn;e.selector=t||"";return e};C.isZ=function(e){return e instanceof C.Z};C.init=function(t,r){if(!t)return C.Z();else if(M(t))return n(u).ready(t);else if(C.isZ(t))return t;else{var i;if(B(t))i=F(t);else if(P(t))i=[H(t)?n.extend({},t):t],t=null;else if(h.test(t))i=C.fragment(t.trim(),RegExp.$1,r),t=null;else if(r!==e)return n(r).find(t);else i=C.qsa(u,t);return C.Z(i,t)}};n=function(e,t){return C.init(e,t)};n.extend=function(e){var t,n=s.call(arguments,1);if(typeof e=="boolean"){t=e;e=n.shift()}n.forEach(function(n){X(e,n,t)});return e};C.qsa=function(e,t){var n;return D(e)&&S.test(t)?(n=e.getElementById(RegExp.$1))?[n]:[]:e.nodeType!==1&&e.nodeType!==9?[]:s.call(E.test(t)?e.getElementsByClassName(RegExp.$1):x.test(t)?e.getElementsByTagName(t):e.querySelectorAll(t))};n.contains=function(e,t){return e!==t&&e.contains(t)};n.type=O;n.isFunction=M;n.isWindow=_;n.isArray=B;n.isPlainObject=H;n.isEmptyObject=function(e){var t;for(t in e)return false;return true};n.inArray=function(e,t,n){return i.indexOf.call(t,e,n)};n.camelCase=k;n.trim=function(e){return e.trim()};n.uuid=0;n.support={};n.expr={};n.map=function(e,t){var n,r=[],i,s;if(j(e))for(i=0;i<e.length;i++){n=t(e[i],i);if(n!=null)r.push(n)}else for(s in e){n=t(e[s],s);if(n!=null)r.push(n)}return I(r)};n.each=function(e,t){var n,r;if(j(e)){for(n=0;n<e.length;n++)if(t.call(e[n],n,e[n])===false)return e}else{for(r in e)if(t.call(e[r],r,e[r])===false)return e}return e};n.grep=function(e,t){return o.call(e,t)};if(window.JSON)n.parseJSON=JSON.parse;n.each("Boolean Number String Function Array Date RegExp Object Error".split(" "),function(e,t){T["[object "+t+"]"]=t.toLowerCase()});n.fn={forEach:i.forEach,reduce:i.reduce,push:i.push,sort:i.sort,indexOf:i.indexOf,concat:i.concat,map:function(e){return n(n.map(this,function(t,n){return e.call(t,n,t)}))},slice:function(){return n(s.apply(this,arguments))},ready:function(e){if(w.test(u.readyState))e(n);else u.addEventListener("DOMContentLoaded",function(){e(n)},false);return this},get:function(t){return t===e?s.call(this):this[t>=0?t:t+this.length]},toArray:function(){return this.get()},size:function(){return this.length},remove:function(){return this.each(function(){if(this.parentNode!=null)this.parentNode.removeChild(this)})},each:function(e){i.every.call(this,function(t,n){return e.call(t,n,t)!==false});return this},filter:function(e){if(M(e))return this.not(this.not(e));return n(o.call(this,function(t){return C.matches(t,e)}))},add:function(e,t){return n(L(this.concat(n(e,t))))},is:function(e){return this.length>0&&C.matches(this[0],e)},not:function(t){var r=[];if(M(t)&&t.call!==e)this.each(function(e){if(!t.call(this,e))r.push(this)});else{var i=typeof t=="string"?this.filter(t):j(t)&&M(t.item)?s.call(t):n(t);this.forEach(function(e){if(i.indexOf(e)<0)r.push(e)})}return n(r)},has:function(e){return this.filter(function(){return P(e)?n.contains(this,e):n(this).find(e).size()})},eq:function(e){return e===-1?this.slice(e):this.slice(e,+e+1)},first:function(){var e=this[0];return e&&!P(e)?e:n(e)},last:function(){var e=this[this.length-1];return e&&!P(e)?e:n(e)},find:function(e){var t,r=this;if(typeof e=="object")t=n(e).filter(function(){var e=this;return i.some.call(r,function(t){return n.contains(t,e)})});else if(this.length==1)t=n(C.qsa(this[0],e));else t=this.map(function(){return C.qsa(this,e)});return t},closest:function(e,t){var r=this[0],i=false;if(typeof e=="object")i=n(e);while(r&&!(i?i.indexOf(r)>=0:C.matches(r,e)))r=r!==t&&!D(r)&&r.parentNode;return n(r)},parents:function(e){var t=[],r=this;while(r.length>0)r=n.map(r,function(e){if((e=e.parentNode)&&!D(e)&&t.indexOf(e)<0){t.push(e);return e}});return V(t,e)},parent:function(e){return V(L(this.pluck("parentNode")),e)},children:function(e){return V(this.map(function(){return W(this)}),e)},contents:function(){return this.map(function(){return s.call(this.childNodes)})},siblings:function(e){return V(this.map(function(e,t){return o.call(W(t.parentNode),function(e){return e!==t})}),e)},empty:function(){return this.each(function(){this.innerHTML=""})},pluck:function(e){return n.map(this,function(t){return t[e]})},show:function(){return this.each(function(){this.style.display=="none"&&(this.style.display=null);if(l(this,"").getPropertyValue("display")=="none")this.style.display=z(this.nodeName)})},replaceWith:function(e){return this.before(e).remove()},wrap:function(e){var t=M(e);if(this[0]&&!t)var r=n(e).get(0),i=r.parentNode||this.length>1;return this.each(function(s){n(this).wrapAll(t?e.call(this,s):i?r.cloneNode(true):r)})},wrapAll:function(e){if(this[0]){n(this[0]).before(e=n(e));var t;while((t=e.children()).length)e=t.first();n(e).append(this)}return this},wrapInner:function(e){var t=M(e);return this.each(function(r){var i=n(this),s=i.contents(),o=t?e.call(this,r):e;s.length?s.wrapAll(o):i.append(o)})},unwrap:function(){this.parent().each(function(){n(this).replaceWith(n(this).children())});return this},clone:function(){return this.map(function(){return this.cloneNode(true)})},hide:function(){return this.css("display","none")},toggle:function(t){return this.each(function(){var r=n(this);(t===e?r.css("display")=="none":t)?r.show():r.hide()})},prev:function(e){return n(this.pluck("previousElementSibling")).filter(e||"*")},next:function(e){return n(this.pluck("nextElementSibling")).filter(e||"*")},html:function(t){return t===e?this.length>0?this[0].innerHTML:null:this.each(function(e){var r=this.innerHTML;n(this).empty().append($(this,t,e,r))})},text:function(t){return t===e?this.length>0?this[0].textContent:null:this.each(function(){this.textContent=t})},attr:function(n,r){var i;return typeof n=="string"&&r===e?this.length==0||this[0].nodeType!==1?e:n=="value"&&this[0].nodeName=="INPUT"?this.val():!(i=this[0].getAttribute(n))&&n in this[0]?this[0][n]:i:this.each(function(e){if(this.nodeType!==1)return;if(P(n))for(t in n)J(this,t,n[t]);else J(this,n,$(this,r,e,this.getAttribute(n)))})},removeAttr:function(e){return this.each(function(){this.nodeType===1&&J(this,e)})},prop:function(t,n){return n===e?this[0]&&this[0][t]:this.each(function(e){this[t]=$(this,n,e,this[t])})},data:function(t,n){var r=this.attr("data-"+q(t),n);return r!==null?Q(r):e},val:function(t){return t===e?this[0]&&(this[0].multiple?n(this[0]).find("option").filter(function(e){return this.selected}).pluck("value"):this[0].value):this.each(function(e){this.value=$(this,t,e,this.value)})},offset:function(e){if(e)return this.each(function(t){var r=n(this),i=$(this,e,t,r.offset()),s=r.offsetParent().offset(),o={top:i.top-s.top,left:i.left-s.left};if(r.css("position")=="static")o["position"]="relative";r.css(o)});if(this.length==0)return null;var t=this[0].getBoundingClientRect();return{left:t.left+window.pageXOffset,top:t.top+window.pageYOffset,width:Math.round(t.width),height:Math.round(t.height)}},css:function(e,n){if(arguments.length<2&&typeof e=="string")return this[0]&&(this[0].style[k(e)]||l(this[0],"").getPropertyValue(e));var r="";if(O(e)=="string"){if(!n&&n!==0)this.each(function(){this.style.removeProperty(q(e))});else r=q(e)+":"+U(e,n)}else{for(t in e)if(!e[t]&&e[t]!==0)this.each(function(){this.style.removeProperty(q(t))});else r+=q(t)+":"+U(t,e[t])+";"}return this.each(function(){this.style.cssText+=";"+r})},index:function(e){return e?this.indexOf(n(e)[0]):this.parent().children().indexOf(this[0])},hasClass:function(e){return i.some.call(this,function(e){return this.test(K(e))},R(e))},addClass:function(e){return this.each(function(t){r=[];var i=K(this),s=$(this,e,t,i);s.split(/\s+/g).forEach(function(e){if(!n(this).hasClass(e))r.push(e)},this);r.length&&K(this,i+(i?" ":"")+r.join(" "))})},removeClass:function(t){return this.each(function(n){if(t===e)return K(this,"");r=K(this);$(this,t,n,r).split(/\s+/g).forEach(function(e){r=r.replace(R(e)," ")});K(this,r.trim())})},toggleClass:function(t,r){return this.each(function(i){var s=n(this),o=$(this,t,i,K(this));o.split(/\s+/g).forEach(function(t){(r===e?!s.hasClass(t):r)?s.addClass(t):s.removeClass(t)})})},scrollTop:function(){if(!this.length)return;return"scrollTop"in this[0]?this[0].scrollTop:this[0].scrollY},position:function(){if(!this.length)return;var e=this[0],t=this.offsetParent(),r=this.offset(),i=d.test(t[0].nodeName)?{top:0,left:0}:t.offset();r.top-=parseFloat(n(e).css("margin-top"))||0;r.left-=parseFloat(n(e).css("margin-left"))||0;i.top+=parseFloat(n(t[0]).css("border-top-width"))||0;i.left+=parseFloat(n(t[0]).css("border-left-width"))||0;return{top:r.top-i.top,left:r.left-i.left}},offsetParent:function(){return this.map(function(){var e=this.offsetParent||u.body;while(e&&!d.test(e.nodeName)&&n(e).css("position")=="static")e=e.offsetParent;return e})}};n.fn.detach=n.fn.remove;["width","height"].forEach(function(t){n.fn[t]=function(r){var i,s=this[0],o=t.replace(/./,function(e){return e[0].toUpperCase()});if(r===e)return _(s)?s["inner"+o]:D(s)?s.documentElement["offset"+o]:(i=this.offset())&&i[t];else return this.each(function(e){s=n(this);s.css(t,$(this,r,e,s[t]()))})}});m.forEach(function(e,t){var r=t%2;n.fn[e]=function(){var e,i=n.map(arguments,function(t){e=O(t);return e=="object"||e=="array"||t==null?t:C.fragment(t)}),s,o=this.length>1;if(i.length<1)return this;return this.each(function(e,u){s=r?u:u.parentNode;u=t==0?u.nextSibling:t==1?u.firstChild:t==2?u:null;i.forEach(function(e){if(o)e=e.cloneNode(true);else if(!s)return n(e).remove();G(s.insertBefore(e,u),function(e){if(e.nodeName!=null&&e.nodeName.toUpperCase()==="SCRIPT"&&(!e.type||e.type==="text/javascript")&&!e.src)window["eval"].call(window,e.innerHTML)})})})};n.fn[r?e+"To":"insert"+(t?"Before":"After")]=function(t){n(t)[e](this);return this}});C.Z.prototype=n.fn;C.uniq=L;C.deserializeValue=Q;n.zepto=C;return n}();window.Zepto=Zepto;"$"in window||(window.$=Zepto);(function(e){function t(e){var t=this.os={},n=this.browser={},r=e.match(/WebKit\/([\d.]+)/),i=e.match(/(Android)\s+([\d.]+)/),s=e.match(/(iPad).*OS\s([\d_]+)/),o=!s&&e.match(/(iPhone\sOS)\s([\d_]+)/),u=e.match(/(webOS|hpwOS)[\s\/]([\d.]+)/),a=u&&e.match(/TouchPad/),f=e.match(/Kindle\/([\d.]+)/),l=e.match(/Silk\/([\d._]+)/),c=e.match(/(BlackBerry).*Version\/([\d.]+)/),h=e.match(/(BB10).*Version\/([\d.]+)/),p=e.match(/(RIM\sTablet\sOS)\s([\d.]+)/),d=e.match(/PlayBook/),v=e.match(/Chrome\/([\d.]+)/)||e.match(/CriOS\/([\d.]+)/),m=e.match(/Firefox\/([\d.]+)/);if(n.webkit=!!r)n.version=r[1];if(i)t.android=true,t.version=i[2];if(o)t.ios=t.iphone=true,t.version=o[2].replace(/_/g,".");if(s)t.ios=t.ipad=true,t.version=s[2].replace(/_/g,".");if(u)t.webos=true,t.version=u[2];if(a)t.touchpad=true;if(c)t.blackberry=true,t.version=c[2];if(h)t.bb10=true,t.version=h[2];if(p)t.rimtabletos=true,t.version=p[2];if(d)n.playbook=true;if(f)t.kindle=true,t.version=f[1];if(l)n.silk=true,n.version=l[1];if(!l&&t.android&&e.match(/Kindle Fire/))n.silk=true;if(v)n.chrome=true,n.version=v[1];if(m)n.firefox=true,n.version=m[1];t.tablet=!!(s||d||i&&!e.match(/Mobile/)||m&&e.match(/Tablet/));t.phone=!!(!t.tablet&&(i||o||u||c||h||v&&e.match(/Android/)||v&&e.match(/CriOS\/([\d.]+)/)||m&&e.match(/Mobile/)))}t.call(e,navigator.userAgent);e.__detect=t})(Zepto);(function(e){function o(e){return e._zid||(e._zid=r++)}function u(e,t,r,i){t=a(t);if(t.ns)var s=f(t.ns);return(n[o(e)]||[]).filter(function(e){return e&&(!t.e||e.e==t.e)&&(!t.ns||s.test(e.ns))&&(!r||o(e.fn)===o(r))&&(!i||e.sel==i)})}function a(e){var t=(""+e).split(".");return{e:t[0],ns:t.slice(1).sort().join(" ")}}function f(e){return new RegExp("(?:^| )"+e.replace(" "," .* ?")+"(?: |$)")}function l(t,n,r){if(e.type(t)!="string")e.each(t,r);else t.split(/\s/).forEach(function(e){r(e,n)})}function c(e,t){return e.del&&(e.e=="focus"||e.e=="blur")||!!t}function h(e){return s[e]||e}function p(t,r,i,u,f,p){var d=o(t),v=n[d]||(n[d]=[]);l(r,i,function(n,r){var i=a(n);i.fn=r;i.sel=u;if(i.e in s)r=function(t){var n=t.relatedTarget;if(!n||n!==this&&!e.contains(this,n))return i.fn.apply(this,arguments)};i.del=f&&f(r,n);var o=i.del||r;i.proxy=function(e){var n=o.apply(t,[e].concat(e.data));if(n===false)e.preventDefault(),e.stopPropagation();return n};i.i=v.length;v.push(i);t.addEventListener(h(i.e),i.proxy,c(i,p))})}function d(e,t,r,i,s){var a=o(e);l(t||"",r,function(t,r){u(e,t,r,i).forEach(function(t){delete n[a][t.i];e.removeEventListener(h(t.e),t.proxy,c(t,s))})})}function b(t){var n,r={originalEvent:t};for(n in t)if(!g.test(n)&&t[n]!==undefined)r[n]=t[n];e.each(y,function(e,n){r[e]=function(){this[n]=v;return t[e].apply(t,arguments)};r[n]=m});return r}function w(e){if(!("defaultPrevented"in e)){e.defaultPrevented=false;var t=e.preventDefault;e.preventDefault=function(){this.defaultPrevented=true;t.call(this)}}}var t=e.zepto.qsa,n={},r=1,i={},s={mouseenter:"mouseover",mouseleave:"mouseout"};i.click=i.mousedown=i.mouseup=i.mousemove="MouseEvents";e.event={add:p,remove:d};e.proxy=function(t,n){if(e.isFunction(t)){var r=function(){return t.apply(n,arguments)};r._zid=o(t);return r}else if(typeof n=="string"){return e.proxy(t[n],t)}else{throw new TypeError("expected function")}};e.fn.bind=function(e,t){return this.each(function(){p(this,e,t)})};e.fn.unbind=function(e,t){return this.each(function(){d(this,e,t)})};e.fn.one=function(e,t){return this.each(function(n,r){p(this,e,t,null,function(e,t){return function(){var n=e.apply(r,arguments);d(r,t,e);return n}})})};var v=function(){return true},m=function(){return false},g=/^([A-Z]|layer[XY]$)/,y={preventDefault:"isDefaultPrevented",stopImmediatePropagation:"isImmediatePropagationStopped",stopPropagation:"isPropagationStopped"};e.fn.delegate=function(t,n,r){return this.each(function(i,s){p(s,n,r,t,function(n){return function(r){var i,o=e(r.target).closest(t,s).get(0);if(o){i=e.extend(b(r),{currentTarget:o,liveFired:s});return n.apply(o,[i].concat([].slice.call(arguments,1)))}}})})};e.fn.undelegate=function(e,t,n){return this.each(function(){d(this,t,n,e)})};e.fn.live=function(t,n){e(document.body).delegate(this.selector,t,n);return this};e.fn.die=function(t,n){e(document.body).undelegate(this.selector,t,n);return this};e.fn.on=function(t,n,r){return!n||e.isFunction(n)?this.bind(t,n||r):this.delegate(n,t,r)};e.fn.off=function(t,n,r){return!n||e.isFunction(n)?this.unbind(t,n||r):this.undelegate(n,t,r)};e.fn.trigger=function(t,n){if(typeof t=="string"||e.isPlainObject(t))t=e.Event(t);w(t);t.data=n;return this.each(function(){if("dispatchEvent"in this)this.dispatchEvent(t)})};e.fn.triggerHandler=function(t,n){var r,i;this.each(function(s,o){r=b(typeof t=="string"?e.Event(t):t);r.data=n;r.target=o;e.each(u(o,t.type||t),function(e,t){i=t.proxy(r);if(r.isImmediatePropagationStopped())return false})});return i};("focusin focusout load resize scroll unload click dblclick "+"mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave "+"change select keydown keypress keyup error").split(" ").forEach(function(t){e.fn[t]=function(e){return e?this.bind(t,e):this.trigger(t)}});["focus","blur"].forEach(function(t){e.fn[t]=function(e){if(e)this.bind(t,e);else this.each(function(){try{this[t]()}catch(e){}});return this}});e.Event=function(e,t){if(typeof e!="string")t=e,e=t.type;var n=document.createEvent(i[e]||"Events"),r=true;if(t)for(var s in t)s=="bubbles"?r=!!t[s]:n[s]=t[s];n.initEvent(e,r,true,null,null,null,null,null,null,null,null,null,null,null,null);n.isDefaultPrevented=function(){return this.defaultPrevented};return n}})(Zepto);(function($){function triggerAndReturn(e,t,n){var r=$.Event(t);$(e).trigger(r,n);return!r.defaultPrevented}function triggerGlobal(e,t,n,r){if(e.global)return triggerAndReturn(t||document,n,r)}function ajaxStart(e){if(e.global&&$.active++===0)triggerGlobal(e,null,"ajaxStart")}function ajaxStop(e){if(e.global&&!--$.active)triggerGlobal(e,null,"ajaxStop")}function ajaxBeforeSend(e,t){var n=t.context;if(t.beforeSend.call(n,e,t)===false||triggerGlobal(t,n,"ajaxBeforeSend",[e,t])===false)return false;triggerGlobal(t,n,"ajaxSend",[e,t])}function ajaxSuccess(e,t,n){var r=n.context,i="success";n.success.call(r,e,i,t);triggerGlobal(n,r,"ajaxSuccess",[t,n,e]);ajaxComplete(i,t,n)}function ajaxError(e,t,n,r){var i=r.context;r.error.call(i,n,t,e);triggerGlobal(r,i,"ajaxError",[n,r,e]);ajaxComplete(t,n,r)}function ajaxComplete(e,t,n){var r=n.context;n.complete.call(r,t,e);triggerGlobal(n,r,"ajaxComplete",[t,n]);ajaxStop(n)}function empty(){}function mimeToDataType(e){if(e)e=e.split(";",2)[0];return e&&(e==htmlType?"html":e==jsonType?"json":scriptTypeRE.test(e)?"script":xmlTypeRE.test(e)&&"xml")||"text"}function appendQuery(e,t){return(e+"&"+t).replace(/[&?]{1,2}/,"?")}function serializeData(e){if(e.processData&&e.data&&$.type(e.data)!="string")e.data=$.param(e.data,e.traditional);if(e.data&&(!e.type||e.type.toUpperCase()=="GET"))e.url=appendQuery(e.url,e.data)}function parseArguments(e,t,n,r){var i=!$.isFunction(t);return{url:e,data:i?t:undefined,success:!i?t:$.isFunction(n)?n:undefined,dataType:i?r||n:n}}function serialize(e,t,n,r){var i,s=$.isArray(t);$.each(t,function(t,o){i=$.type(o);if(r)t=n?r:r+"["+(s?"":t)+"]";if(!r&&s)e.add(o.name,o.value);else if(i=="array"||!n&&i=="object")serialize(e,o,n,t);else e.add(t,o)})}var jsonpID=0,document=window.document,key,name,rscript=/<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi,scriptTypeRE=/^(?:text|application)\/javascript/i,xmlTypeRE=/^(?:text|application)\/xml/i,jsonType="application/json",htmlType="text/html",blankRE=/^\s*$/;$.active=0;$.ajaxJSONP=function(e){if(!("type"in e))return $.ajax(e);var t="jsonp"+ ++jsonpID,n=document.createElement("script"),r=function(){clearTimeout(o);$(n).remove();delete window[t]},i=function(n){r();if(!n||n=="timeout")window[t]=empty;ajaxError(null,n||"abort",s,e)},s={abort:i},o;if(ajaxBeforeSend(s,e)===false){i("abort");return false}window[t]=function(t){r();ajaxSuccess(t,s,e)};n.onerror=function(){i("error")};n.src=e.url.replace(/=\?/,"="+t);$("head").append(n);if(e.timeout>0)o=setTimeout(function(){i("timeout")},e.timeout);return s};$.ajaxSettings={type:"GET",beforeSend:empty,success:empty,error:empty,complete:empty,context:null,global:true,xhr:function(){return new window.XMLHttpRequest},accepts:{script:"text/javascript, application/javascript",json:jsonType,xml:"application/xml, text/xml",html:htmlType,text:"text/plain"},crossDomain:false,timeout:0,processData:true,cache:true};$.ajax=function(options){var settings=$.extend({},options||{});for(key in $.ajaxSettings)if(settings[key]===undefined)settings[key]=$.ajaxSettings[key];ajaxStart(settings);if(!settings.crossDomain)settings.crossDomain=/^([\w-]+:)?\/\/([^\/]+)/.test(settings.url)&&RegExp.$2!=window.location.host;if(!settings.url)settings.url=window.location.toString();serializeData(settings);if(settings.cache===false)settings.url=appendQuery(settings.url,"_="+Date.now());var dataType=settings.dataType,hasPlaceholder=/=\?/.test(settings.url);if(dataType=="jsonp"||hasPlaceholder){if(!hasPlaceholder)settings.url=appendQuery(settings.url,"callback=?");return $.ajaxJSONP(settings)}var mime=settings.accepts[dataType],baseHeaders={},protocol=/^([\w-]+:)\/\//.test(settings.url)?RegExp.$1:window.location.protocol,xhr=settings.xhr(),abortTimeout;if(!settings.crossDomain)baseHeaders["X-Requested-With"]="XMLHttpRequest";if(mime){baseHeaders["Accept"]=mime;if(mime.indexOf(",")>-1)mime=mime.split(",",2)[0];xhr.overrideMimeType&&xhr.overrideMimeType(mime)}if(settings.contentType||settings.contentType!==false&&settings.data&&settings.type.toUpperCase()!="GET")baseHeaders["Content-Type"]=settings.contentType||"application/x-www-form-urlencoded";settings.headers=$.extend(baseHeaders,settings.headers||{});xhr.onreadystatechange=function(){if(xhr.readyState==4){xhr.onreadystatechange=empty;clearTimeout(abortTimeout);var result,error=false;if(xhr.status>=200&&xhr.status<300||xhr.status==304||xhr.status==0&&protocol=="file:"){dataType=dataType||mimeToDataType(xhr.getResponseHeader("content-type"));result=xhr.responseText;try{if(dataType=="script")(1,eval)(result);else if(dataType=="xml")result=xhr.responseXML;else if(dataType=="json")result=blankRE.test(result)?null:$.parseJSON(result)}catch(e){error=e}if(error)ajaxError(error,"parsererror",xhr,settings);else ajaxSuccess(result,xhr,settings)}else{ajaxError(null,xhr.status?"error":"abort",xhr,settings)}}};var async="async"in settings?settings.async:true;xhr.open(settings.type,settings.url,async);for(name in settings.headers)xhr.setRequestHeader(name,settings.headers[name]);if(ajaxBeforeSend(xhr,settings)===false){xhr.abort();return false}if(settings.timeout>0)abortTimeout=setTimeout(function(){xhr.onreadystatechange=empty;xhr.abort();ajaxError(null,"timeout",xhr,settings)},settings.timeout);xhr.send(settings.data?settings.data:null);return xhr};$.get=function(e,t,n,r){return $.ajax(parseArguments.apply(null,arguments))};$.post=function(e,t,n,r){var i=parseArguments.apply(null,arguments);i.type="POST";return $.ajax(i)};$.getJSON=function(e,t,n){var r=parseArguments.apply(null,arguments);r.dataType="json";return $.ajax(r)};$.fn.load=function(e,t,n){if(!this.length)return this;var r=this,i=e.split(/\s/),s,o=parseArguments(e,t,n),u=o.success;if(i.length>1)o.url=i[0],s=i[1];o.success=function(e){r.html(s?$("<div>").html(e.replace(rscript,"")).find(s):e);u&&u.apply(r,arguments)};$.ajax(o);return this};var escape=encodeURIComponent;$.param=function(e,t){var n=[];n.add=function(e,t){this.push(escape(e)+"="+escape(t))};serialize(n,e,t);return n.join("&").replace(/%20/g,"+")}})(Zepto);(function(e){e.fn.serializeArray=function(){var t=[],n;e(Array.prototype.slice.call(this.get(0).elements)).each(function(){n=e(this);var r=n.attr("type");if(this.nodeName.toLowerCase()!="fieldset"&&!this.disabled&&r!="submit"&&r!="reset"&&r!="button"&&(r!="radio"&&r!="checkbox"||this.checked))t.push({name:n.attr("name"),value:n.val()})});return t};e.fn.serialize=function(){var e=[];this.serializeArray().forEach(function(t){e.push(encodeURIComponent(t.name)+"="+encodeURIComponent(t.value))});return e.join("&")};e.fn.submit=function(t){if(t)this.bind("submit",t);else if(this.length){var n=e.Event("submit");this.eq(0).trigger(n);if(!n.defaultPrevented)this.get(0).submit()}return this}})(Zepto);(function(e,t){function y(e){return b(e.replace(/([a-z])([A-Z])/,"$1-$2"))}function b(e){return e.toLowerCase()}function w(e){return r?r+e:b(e)}var n="",r,i,s,o={Webkit:"webkit",Moz:"",O:"o",ms:"MS"},u=window.document,a=u.createElement("div"),f=/^((translate|rotate|scale)(X|Y|Z|3d)?|matrix(3d)?|perspective|skew(X|Y)?)$/i,l,c,h,p,d,v,m,g={};e.each(o,function(e,i){if(a.style[e+"TransitionProperty"]!==t){n="-"+b(e)+"-";r=i;return false}});l=n+"transform";g[c=n+"transition-property"]=g[h=n+"transition-duration"]=g[p=n+"transition-timing-function"]=g[d=n+"animation-name"]=g[v=n+"animation-duration"]=g[m=n+"animation-timing-function"]="";e.fx={off:r===t&&a.style.transitionProperty===t,speeds:{_default:400,fast:200,slow:600},cssPrefix:n,transitionEnd:w("TransitionEnd"),animationEnd:w("AnimationEnd")};e.fn.animate=function(t,n,r,i){if(e.isPlainObject(n))r=n.easing,i=n.complete,n=n.duration;if(n)n=(typeof n=="number"?n:e.fx.speeds[n]||e.fx.speeds._default)/1e3;return this.anim(t,n,r,i)};e.fn.anim=function(n,r,i,s){var o,u={},a,b="",w=this,E,S=e.fx.transitionEnd;if(r===t)r=.4;if(e.fx.off)r=0;if(typeof n=="string"){u[d]=n;u[v]=r+"s";u[m]=i||"linear";S=e.fx.animationEnd}else{a=[];for(o in n)if(f.test(o))b+=o+"("+n[o]+") ";else u[o]=n[o],a.push(y(o));if(b)u[l]=b,a.push(l);if(r>0&&typeof n==="object"){u[c]=a.join(", ");u[h]=r+"s";u[p]=i||"linear"}}E=function(t){if(typeof t!=="undefined"){if(t.target!==t.currentTarget)return;e(t.target).unbind(S,E)}e(this).css(g);s&&s.call(this)};if(r>0)this.bind(S,E);this.size()&&this.get(0).clientLeft;this.css(u);if(r<=0)setTimeout(function(){w.each(function(){E.call(this)})},0);return this};a=null})(Zepto);(function(e,t){function u(n,r,i,s,o){if(typeof r=="function"&&!o)o=r,r=t;var u={opacity:i};if(s){u.scale=s;n.css(e.fx.cssPrefix+"transform-origin","0 0")}return n.animate(u,r,null,o)}function a(t,n,r,i){return u(t,n,0,r,function(){s.call(e(this));i&&i.call(this)})}var n=window.document,r=n.documentElement,i=e.fn.show,s=e.fn.hide,o=e.fn.toggle;e.fn.show=function(e,n){i.call(this);if(e===t)e=0;else this.css("opacity",0);return u(this,e,1,"1,1",n)};e.fn.hide=function(e,n){if(e===t)return s.call(this);else return a(this,e,"0,0",n)};e.fn.toggle=function(n,r){if(n===t||typeof n=="boolean")return o.call(this,n);else return this.each(function(){var t=e(this);t[t.css("display")=="none"?"show":"hide"](n,r)})};e.fn.fadeTo=function(e,t,n){return u(this,e,t,null,n)};e.fn.fadeIn=function(e,t){var n=this.css("opacity");if(n>0)this.css("opacity",0);else n=1;return i.call(this).fadeTo(e,n,t)};e.fn.fadeOut=function(e,t){return a(this,e,null,t)};e.fn.fadeToggle=function(t,n){return this.each(function(){var r=e(this);r[r.css("opacity")==0||r.css("display")=="none"?"fadeIn":"fadeOut"](t,n)})}})(Zepto);

var $indexPageContent;

/**
 * JQuery's ready function.
 * 
 * Adds a heartbeat to keep session alive, prepares the page for client side templating, and registers all
 * jquery bindings as well as the associated tracker for the page.
 * 
 */
$(function(){
	var startTime = new Date().getTime();
	
	// initialize fastclick
	new FastClick(document.body);
	
	// global variable holding the associated tracker
	tracktacular.tracker = $('html').data('tracker');
	
	// set the templateData
	setTemplateData($('#templateData').data('templateData'));
		
	// retrieve a content body template to determine if we are rending the body
	var contentBodyTemplate = getTemplateData().contentBodyTemplate;
	
	if(contentBodyTemplate)
	{
		if(getTemplateData().contentBody.noData)
			renderContent(contentBodyTemplate);
		else
			// make an ajax call to retrieve the full template data
			$.getJSON(getTemplateData().formAction, function(data) {
				
				// merge the contentBody section with the template data
				var templateData = data;
				
				var references = {};
			    buildReferences(references, templateData);
			    getTemplateData().references = references;
				getTemplateData().contentBody = templateData.contentBody;
				
				renderContent(contentBodyTemplate);
			});
	}
	else
	{
		onPageLoad(); 
		onPageUpdate();
	}
	
	// store the index page content in a global variable to attach quickly when going to the home page
	dust.render('indexPageContent', getTemplateData(), function(err, out) {
		if(err)
			console.log(err);
		
		$indexPageContent = $(out);		
	});

});

/**
 * Renders the content body.
 *
 */
function renderContent(contentBodyTemplate) {
	
	// render content messages
	dust.render('contentMessages', getTemplateData(), function(err, out) {
		if(err)
			console.log(err);

		$('#content-messages').html(out);
	});

 	// render content body
	dust.render(contentBodyTemplate, getTemplateData(), function(err, out) {
		if(err)
			console.log(err);

		$('#content-body').html(out);
		
		onPageLoad(); 
		onPageUpdate();
	});
}


/**
 * Gets the current template data.
 *
 */
function getTemplateData() {
	return tracktacular.templateData;
}

/**
 * Sets the current template data.
 *
 * @param templateData
 */
function setTemplateData(templateData) {
	var references = {};
    buildReferences(references, templateData);
    templateData.references = references;
    tracktacular.templateData = templateData;
}

/**
 * Builds a reference map out of any objects with an id. 
 * This can be used to lookup contexts for dialogs and other templates who need a specific context. 
 * 
 * @param references
 * @param data
 */
function buildReferences(references, data) {

	// if an id exists add it to the references object
	if(data.hasOwnProperty("id"))
		references[data.id] = data;
	
	// recurse through any values the object has
	for (var key in data)
	{
        if (data.hasOwnProperty(key))
        {
        	var value = data[key]; 
        	
        	// if an array is found, loop through each object and build the references for that object
        	if($.isArray(value))
                $.each(value, function(index, item) { 
                    if(typeof item == "object")
                        buildReferences(references, item);
                });
           
        	// if an object is found, build the references for that object
        	else if(typeof value == "object")
       			buildReferences(references, value);
        }
	}
	
}

/**
 * Function executed one time on page load after the dom is ready.
 * 
 */
var onPageLoad = function() {

	$(window).bind( 'hashchange', function(e) { 
		if(!window.location.hash && !$('#index').length)
			toHome();
	});
	
	// global event handlers
	$("body").delegate("form", "submit", function() {
		return toSubmitForm($(this));
	}); 

	$("body").delegate(".to-toggle", "click", function() {
		return toToggle($(this));
	}); 

	$("body").delegate("a", "click", function() {			
		return toLink($(this));
	});		

	$("body").delegate("a.to-home", "click", function() {			
		return toHome($(this));
	});		

	$("body").delegate("a.to-menu", "click", function() {			
		return toMenu($(this));
	});		

	$("body").delegate(".to-highlight", "click", function() {
		return toHighlight($(this));
	}); 

	$("body").delegate("a.to-menu-item-submit-form", "click", function() {
		return toMenuItemSubmitForm($(this));
	}); 	
	
	$("body").delegate("a.to-menu-item-dialog-form", "click", function() {
		return toMenuItemDialogForm($(this));
	}); 
	
	$("body").delegate("a.to-dialog-form", "click", function() {
		return toDialogForm($(this));
	}); 

}

/**
 * Function executed every time the page is updated, including the initial page load.
 * 
 * @param $context - The context, which may be null, containing the updated page elements.
 */
var onPageUpdate = function($context) {

}
