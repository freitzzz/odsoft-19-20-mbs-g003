$wnd.showcase.runAsyncCallback24("function rqb(a){this.a=a}\nfunction tqb(a){this.a=a}\nfunction vqb(a){this.a=a}\nfunction Aqb(a,b){this.a=a;this.b=b}\nfunction gUb(a){return LIb(),a.hb}\nfunction kUb(a,b){dUb(a,b);ep((LIb(),a.hb),b)}\nfunction CIb(){var a;if(!zIb||FIb()){a=new Cfc;EIb(a);zIb=a}return zIb}\nfunction FIb(){var a=$doc.cookie;if(a!=AIb){AIb=a;return true}else{return false}}\nfunction ep(b,c){try{b.remove(c)}catch(a){b.removeChild(b.childNodes[c])}}\nfunction GIb(a){BIb&&(a=encodeURIComponent(a));$doc.cookie=a+'=;expires=Fri, 02-Jan-1970 00:00:00 GMT'}\nfunction oqb(a){var b,c,d,e;if(gUb(a.c).options.length<1){pWb(a.a,'');pWb(a.b,'');return}e=gUb(a.c).selectedIndex;b=hUb(a.c,e);c=(d=CIb(),BO(b==null?Kac(Ufc(d.d,null)):igc(d.e,b)));pWb(a.a,b);pWb(a.b,c)}\nfunction nqb(a,b){var c,d,e,f,g,h;eh(a.c).options.length=0;h=0;e=new Zbc(CIb());for(d=(g=e.a.Vh().fc(),new ccc(g));d.a.Og();){c=(f=xO(d.a.Pg(),36),BO(f._h()));cUb(a.c,c);o8b(c,b)&&(h=eh(a.c).options.length-1)}sm((lm(),km),new Aqb(a,h))}\nfunction EIb(b){var c=$doc.cookie;if(c&&c!=''){var d=c.split('; ');for(var e=d.length-1;e>=0;--e){var f,g;var h=d[e].indexOf('=');if(h==-1){f=d[e];g=''}else{f=d[e].substring(0,h);g=d[e].substring(h+1)}if(BIb){try{f=decodeURIComponent(f)}catch(a){}try{g=decodeURIComponent(g)}catch(a){}}b.Xh(f,g)}}}\nfunction mqb(a){var b,c,d;c=new kSb(3,3);a.c=new mUb;b=new ULb('Supprimer');Dh((LIb(),b.hb),Uoc,true);FRb(c,0,0,'<b><b>Cookies existants:<\\/b><\\/b>');IRb(c,0,1,a.c);IRb(c,0,2,b);a.a=new yWb;FRb(c,1,0,'<b><b>Nom:<\\/b><\\/b>');IRb(c,1,1,a.a);a.b=new yWb;d=new ULb('Sauvegarder Cookie');Dh(d.hb,Uoc,true);FRb(c,2,0,'<b><b>Valeur:<\\/b><\\/b>');IRb(c,2,1,a.b);IRb(c,2,2,d);Kh(d,new rqb(a),(Gt(),Gt(),Ft));Kh(a.c,new tqb(a),(zt(),zt(),yt));Kh(b,new vqb(a),(null,Ft));nqb(a,null);return c}\no8(467,1,Llc,rqb);_.Sc=function sqb(a){var b,c,d;c=lWb(this.a.a);d=lWb(this.a.b);b=new nN(M7(S7((new lN).q.getTime()),_pc));if(c.length<1){$wnd.alert('Vous devez indiquer un nom de cookie');return}HIb(c,d,b);nqb(this.a,c)};var EZ=u7b(Zlc,'CwCookies/1',467);o8(468,1,Mlc,tqb);_.Rc=function uqb(a){oqb(this.a)};var FZ=u7b(Zlc,'CwCookies/2',468);o8(469,1,Llc,vqb);_.Sc=function wqb(a){var b,c;c=eh(this.a.c).selectedIndex;if(c>-1&&c<eh(this.a.c).options.length){b=hUb(this.a.c,c);GIb(b);kUb(this.a.c,c);oqb(this.a)}};var GZ=u7b(Zlc,'CwCookies/3',469);o8(470,1,Ulc);_.Bc=function zqb(){Iab(this.b,mqb(this.a))};o8(471,1,{},Aqb);_.Dc=function Bqb(){this.b<eh(this.a.c).options.length&&lUb(this.a.c,this.b);oqb(this.a)};_.b=0;var IZ=u7b(Zlc,'CwCookies/5',471);var zIb=null,AIb;Oic(zl)(24);\n//# sourceURL=showcase-24.js\n")
