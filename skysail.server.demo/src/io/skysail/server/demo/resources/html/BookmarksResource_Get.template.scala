
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object BookmarksResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,io.skysail.server.demo.domain.BookmarkList,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel, response: ResponseEventBase, bms: io.skysail.server.demo.domain.BookmarkList):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.106*/("""

"""),_display_(/*8.2*/main(response)/*8.16*/ {_display_(Seq[Any](format.raw/*8.18*/("""

"""),format.raw/*10.1*/("""<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
    <h1 class="h2">"""),_display_(/*11.21*/rep/*11.24*/.model.name),format.raw/*11.35*/("""</h1>
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
    <table class="table table-sm">
        <thead>
        <tr>
            <th>Title</th>
            <th>Url</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        """),_display_(/*34.10*/for(p <- bms.bookmarks) yield /*34.33*/ {_display_(Seq[Any](format.raw/*34.35*/("""
        """),format.raw/*35.9*/("""<tr>
            <th scope="row">"""),_display_(/*36.30*/p/*36.31*/.title),format.raw/*36.37*/("""</th>
            <td><a href='"""),_display_(/*37.27*/p/*37.28*/.url),format.raw/*37.32*/("""' target='demo_bms_"""),_display_(/*37.52*/p/*37.53*/.title),format.raw/*37.59*/(""")'>"""),_display_(/*37.63*/p/*37.64*/.url),format.raw/*37.68*/("""</a></td>
            <td>
                """),_display_(/*39.18*/for(l <- p._links) yield /*39.36*/ {_display_(Seq[Any](format.raw/*39.38*/("""
                """),_display_(/*40.18*/Html(l.toHtml(p.id.get))),format.raw/*40.42*/("""&nbsp;
                """)))}),format.raw/*41.18*/("""
            """),format.raw/*42.13*/("""</td>
        </tr>
        """)))}),format.raw/*44.10*/("""

        """),format.raw/*46.9*/("""</tbody>

    </table>
    <hr>
    """),_display_(/*50.6*/for(l <- bms._links) yield /*50.26*/ {_display_(Seq[Any](format.raw/*50.28*/("""
    """),_display_(/*51.6*/Html(l.toHtml)),format.raw/*51.20*/("""
    """)))}),format.raw/*52.6*/("""

    """),format.raw/*54.5*/("""<hr>
    <a href="/doc/v1/index.html" target="_docs">Doc</a>
</div>

""")))}))
      }
    }
  }

  def render(rep:RepresentationModel,response:ResponseEventBase,bms:io.skysail.server.demo.domain.BookmarkList): play.twirl.api.HtmlFormat.Appendable = apply(rep,response,bms)

  def f:((RepresentationModel,ResponseEventBase,io.skysail.server.demo.domain.BookmarkList) => play.twirl.api.HtmlFormat.Appendable) = (rep,response,bms) => apply(rep,response,bms)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Feb 05 11:43:05 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/BookmarksResource_Get.scala.html
                  HASH: 380b3b60da96d92e7de662a5e52c0a06e303d63b
                  MATRIX: 761->193|961->297|989->300|1011->314|1050->316|1079->318|1239->451|1251->454|1283->465|1966->1121|2005->1144|2045->1146|2081->1155|2142->1189|2152->1190|2179->1196|2238->1228|2248->1229|2273->1233|2320->1253|2330->1254|2357->1260|2388->1264|2398->1265|2423->1269|2494->1313|2528->1331|2568->1333|2613->1351|2658->1375|2713->1399|2754->1412|2814->1441|2851->1451|2914->1488|2950->1508|2990->1510|3022->1516|3057->1530|3093->1536|3126->1542
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|26->11|26->11|26->11|49->34|49->34|49->34|50->35|51->36|51->36|51->36|52->37|52->37|52->37|52->37|52->37|52->37|52->37|52->37|52->37|54->39|54->39|54->39|55->40|55->40|56->41|57->42|59->44|61->46|65->50|65->50|65->50|66->51|66->51|67->52|69->54
                  -- GENERATED --
              */
          