$wnd.showcase.runAsyncCallback11("function pEb(){}\nfunction rEb(){}\nfunction kEb(a,b){a.b=b}\nfunction lEb(a){if(a==aEb){return true}cz();return a==dEb}\nfunction mEb(a){if(a==_Db){return true}cz();return a==$Db}\nfunction qEb(a){this.b=(TFb(),OFb).a;this.e=(YFb(),XFb).a;this.a=a}\nfunction iEb(a,b){var c;c=bC(a.fb,153);c.b=b.a;!!c.d&&czb(c.d,b)}\nfunction jEb(a,b){var c;c=bC(a.fb,153);c.e=b.a;!!c.d&&ezb(c.d,b)}\nfunction eEb(){eEb=BX;ZDb=new pEb;aEb=new pEb;_Db=new pEb;$Db=new pEb;bEb=new pEb;cEb=new pEb;dEb=new pEb}\nfunction nEb(){eEb();gzb.call(this);this.b=(TFb(),OFb);this.c=(YFb(),XFb);(Svb(),this.e)[fac]=0;this.e[gac]=0}\nfunction fEb(a,b,c){var d;if(c==ZDb){if(b==a.a){return}else if(a.a){throw XW(new PWb('Only one CENTER widget may be added'))}}Rh(b);bQb(a.j,b);c==ZDb&&(a.a=b);d=new qEb(c);b.fb=d;iEb(b,a.b);jEb(b,a.c);hEb(a);Th(b,a)}\nfunction gEb(a){var b,c,d,e,f,g,h;KPb((Svb(),a.hb),'',Pbc);g=new B2b;h=new lQb(a.j);while(h.b<h.c.c){b=jQb(h);f=bC(b.fb,153).a;d=bC(JZb(T2b(g.d,f)),84);c=!d?1:d.a;e=f==bEb?'north'+c:f==cEb?'south'+c:f==dEb?'west'+c:f==$Db?'east'+c:f==aEb?'linestart'+c:f==_Db?'lineend'+c:L8b;KPb(Qo(b.hb),Pbc,e);VZb(g,f,aXb(c+1))}}\nfunction hEb(a){var b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r;b=(Svb(),a.d);while(uxb(b)>0){wo(b,txb(b,0))}o=1;e=1;for(i=new lQb(a.j);i.b<i.c.c;){d=jQb(i);f=bC(d.fb,153).a;f==bEb||f==cEb?++o:(f==$Db||f==dEb||f==aEb||f==_Db)&&++e}p=kB(tR,g6b,262,o,0,1);for(g=0;g<o;++g){p[g]=new rEb;p[g].b=$doc.createElement(dac);so(b,Zvb(p[g].b))}k=0;l=e-1;m=0;q=o-1;c=null;for(h=new lQb(a.j);h.b<h.c.c;){d=jQb(h);j=bC(d.fb,153);r=$doc.createElement(eac);j.d=r;j.d[T9b]=j.b;j.d.style[U9b]=j.e;j.d[y6b]=j.f;j.d[x6b]=j.c;if(j.a==bEb){Vvb(p[m].b,r,p[m].a);so(r,Zvb(d.hb));r[Wac]=l-k+1;++m}else if(j.a==cEb){Vvb(p[q].b,r,p[q].a);so(r,Zvb(d.hb));r[Wac]=l-k+1;--q}else if(j.a==ZDb){c=r}else if(lEb(j.a)){n=p[m];Vvb(n.b,r,n.a++);so(r,Zvb(d.hb));r[Qbc]=q-m+1;++k}else if(mEb(j.a)){n=p[m];Vvb(n.b,r,n.a);so(r,Zvb(d.hb));r[Qbc]=q-m+1;--l}}if(a.a){n=p[m];Vvb(n.b,c,n.a);so(c,Zvb(eh(a.a)))}}\nvar Pbc='cwDockPanel';AX(417,1,K8b);_.Bc=function neb(){var a,b,c;QZ(this.a,(a=new nEb,(Svb(),a.hb).className='cw-DockPanel',a.e[fac]=4,kEb(a,(TFb(),NFb)),fEb(a,new MCb(Jbc),(eEb(),bEb)),fEb(a,new MCb(Kbc),cEb),fEb(a,new MCb(Lbc),$Db),fEb(a,new MCb(Mbc),dEb),fEb(a,new MCb(Nbc),bEb),fEb(a,new MCb(Obc),cEb),b=new MCb('\\u8FD9\\u4E2A\\u793A\\u4F8B\\u4E2D\\u5728<code>DockPanel<\\/code> \\u7684\\u4E2D\\u95F4\\u4F4D\\u7F6E\\u6709\\u4E00\\u4E2A<code>ScrollPanel<\\/code>\\u3002\\u5982\\u679C\\u5728\\u4E2D\\u95F4\\u653E\\u5165\\u5F88\\u591A\\u5185\\u5BB9\\uFF0C\\u5B83\\u5C31\\u4F1A\\u53D8\\u6210\\u9875\\u9762\\u5185\\u7684\\u53EF\\u6EDA\\u52A8\\u533A\\u57DF\\uFF0C\\u65E0\\u9700\\u4F7F\\u7528IFRAME\\u3002<br><br>\\u6B64\\u5904\\u4F7F\\u7528\\u4E86\\u76F8\\u5F53\\u591A\\u65E0\\u610F\\u4E49\\u7684\\u6587\\u5B57\\uFF0C\\u4E3B\\u8981\\u662F\\u4E3A\\u4E86\\u53EF\\u4EE5\\u6EDA\\u52A8\\u81F3\\u53EF\\u89C6\\u533A\\u57DF\\u7684\\u5E95\\u90E8\\u3002\\u5426\\u5219\\uFF0C\\u60A8\\u6050\\u6015\\u4E0D\\u5F97\\u4E0D\\u628A\\u5B83\\u7F29\\u5230\\u5F88\\u5C0F\\u624D\\u80FD\\u770B\\u5230\\u90A3\\u5C0F\\u5DE7\\u7684\\u6EDA\\u52A8\\u6761\\u3002'),c=new fAb(b),c.hb.style[y6b]='400px',c.hb.style[x6b]='100px',fEb(a,c,ZDb),gEb(a),a))};AX(873,254,D6b,nEb);_.gc=function oEb(a){var b;b=ayb(this,a);if(b){a==this.a&&(this.a=null);hEb(this)}return b};var ZDb,$Db,_Db,aEb,bEb,cEb,dEb;var uR=tWb(B6b,'DockPanel',873);AX(152,1,{},pEb);var rR=tWb(B6b,'DockPanel/DockLayoutConstant',152);AX(153,1,{153:1},qEb);_.c='';_.f='';var sR=tWb(B6b,'DockPanel/LayoutData',153);AX(262,1,{262:1},rEb);_.a=0;var tR=tWb(B6b,'DockPanel/TmpRow',262);N5b(zl)(11);\n//# sourceURL=showcase-11.js\n")
