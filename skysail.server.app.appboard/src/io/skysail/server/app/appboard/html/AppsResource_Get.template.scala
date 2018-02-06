
package io.skysail.server.app.appboard.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel
/*6.2*/import html._

object AppsResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,io.skysail.domain.app.ApplicationList,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*8.2*/(rep: RepresentationModel, response: ResponseEventBase, appList: io.skysail.domain.app.ApplicationList):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*8.105*/("""

"""),_display_(/*10.2*/main(response)/*10.16*/ {_display_(Seq[Any](format.raw/*10.18*/("""

"""),format.raw/*12.1*/("""<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
    <h1 class="h2">Applications</h1>
    <div class="btn-toolbar mb-2 mb-md-0">
        <div class="btn-group mr-2">
            <button class="btn btn-sm btn-outline-secondary">Share</button>
            <button class="btn btn-sm btn-outline-secondary">Export</button>
        </div>
        <button class="btn btn-sm btn-outline-secondary dropdown-toggle">
            <span data-feather="calendar"></span>
            This week
        </button>
    </div>
</div>

<div class="row">
    """),_display_(/*27.6*/for(p <- appList.applications) yield /*27.36*/ {_display_(Seq[Any](format.raw/*27.38*/("""
    """),_display_(/*28.6*/if(p.name != "_root")/*28.27*/ {_display_(Seq[Any](format.raw/*28.29*/("""
    """),format.raw/*29.5*/("""<div class="col">
        <div class="card" style="width: 18rem;">
            <!--<img class="card-img-top" src="..." alt="Card image cap">-->

            <div class="card-body">
                <h5 class="card-title"><i class="fa fa-adjust" aria-hidden="true"></i> """),_display_(/*34.89*/p/*34.90*/.name),format.raw/*34.95*/("""</h5>
                <h6 class="card-subtitle mb-2 text-muted">"""),_display_(/*35.60*/p/*35.61*/.version),format.raw/*35.69*/("""</h6>
                <p class="card-text">"""),_display_(/*36.39*/p/*36.40*/.description),format.raw/*36.52*/("""</p>
                <a href='"""),_display_(/*37.27*/p/*37.28*/.context),format.raw/*37.36*/("""' class="btn btn-primary">Open</a>
            </div>
        </div>
    </div>
    """)))}),format.raw/*41.6*/("""
    """)))}),format.raw/*42.6*/("""
"""),format.raw/*43.1*/("""</div>

""")))}))
      }
    }
  }

  def render(rep:RepresentationModel,response:ResponseEventBase,appList:io.skysail.domain.app.ApplicationList): play.twirl.api.HtmlFormat.Appendable = apply(rep,response,appList)

  def f:((RepresentationModel,ResponseEventBase,io.skysail.domain.app.ApplicationList) => play.twirl.api.HtmlFormat.Appendable) = (rep,response,appList) => apply(rep,response,appList)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Sun Feb 04 20:02:16 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.app.appboard/./src/io/skysail/server/app/appboard/AppsResource_Get.scala.html
                  HASH: faf569a33ab9b1f90ed9b26f8e7c89c9997fa1e1
                  MATRIX: 185->193|583->209|782->312|811->315|834->329|874->331|903->333|1533->937|1579->967|1619->969|1651->975|1681->996|1721->998|1753->1003|2049->1272|2059->1273|2085->1278|2177->1343|2187->1344|2216->1352|2287->1396|2297->1397|2330->1409|2388->1440|2398->1441|2427->1449|2542->1534|2578->1540|2606->1541
                  LINES: 8->6|13->8|18->8|20->10|20->10|20->10|22->12|37->27|37->27|37->27|38->28|38->28|38->28|39->29|44->34|44->34|44->34|45->35|45->35|45->35|46->36|46->36|46->36|47->37|47->37|47->37|51->41|52->42|53->43
                  -- GENERATED --
              */
          