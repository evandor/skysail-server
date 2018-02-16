
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
            <th>Created</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        """),_display_(/*35.10*/for(p <- bms.bookmarks) yield /*35.33*/ {_display_(Seq[Any](format.raw/*35.35*/("""
        """),format.raw/*36.9*/("""<tr>
            <th scope="row">"""),_display_(/*37.30*/p/*37.31*/.title),format.raw/*37.37*/("""</th>
            <td><img src='"""),_display_(/*38.28*/p/*38.29*/.favIcon.getOrElse("thesource")),format.raw/*38.60*/("""'>&nbsp;<a href='"""),_display_(/*38.78*/p/*38.79*/.url),format.raw/*38.83*/("""' target='demo_bms_"""),_display_(/*38.103*/p/*38.104*/.title),format.raw/*38.110*/("""'>"""),_display_(/*38.113*/p/*38.114*/.url),format.raw/*38.118*/("""</a></td>
            <td><script>document.write(moment.unix("""),_display_(/*39.53*/p/*39.54*/.created),format.raw/*39.62*/(""").format("DD.MM.YYYY"));</script></td>
            <td>
                """),_display_(/*41.18*/for(l <- p._links) yield /*41.36*/ {_display_(Seq[Any](format.raw/*41.38*/("""
                """),_display_(/*42.18*/Html(l.toHtml(p.id.get))),format.raw/*42.42*/("""&nbsp;
                """)))}),format.raw/*43.18*/("""
            """),format.raw/*44.13*/("""</td>
        </tr>
        """)))}),format.raw/*46.10*/("""

        """),format.raw/*48.9*/("""</tbody>

    </table>
    <hr>
    """),_display_(/*52.6*/for(l <- bms._links) yield /*52.26*/ {_display_(Seq[Any](format.raw/*52.28*/("""
    """),_display_(/*53.6*/Html(l.toHtml)),format.raw/*53.20*/("""
    """)))}),format.raw/*54.6*/("""

    """),format.raw/*56.5*/("""<hr>
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
                  DATE: Fri Feb 16 07:43:54 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/BookmarksResource_Get.scala.html
                  HASH: ee401861ab5a2d062685fb47727cd69b22cccfae
                  MATRIX: 761->193|961->297|989->300|1011->314|1050->316|1079->318|1239->451|1251->454|1283->465|1995->1150|2034->1173|2074->1175|2110->1184|2171->1218|2181->1219|2208->1225|2268->1258|2278->1259|2330->1290|2375->1308|2385->1309|2410->1313|2458->1333|2469->1334|2497->1340|2528->1343|2539->1344|2565->1348|2654->1410|2664->1411|2693->1419|2793->1492|2827->1510|2867->1512|2912->1530|2957->1554|3012->1578|3053->1591|3113->1620|3150->1630|3213->1667|3249->1687|3289->1689|3321->1695|3356->1709|3392->1715|3425->1721
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|26->11|26->11|26->11|50->35|50->35|50->35|51->36|52->37|52->37|52->37|53->38|53->38|53->38|53->38|53->38|53->38|53->38|53->38|53->38|53->38|53->38|53->38|54->39|54->39|54->39|56->41|56->41|56->41|57->42|57->42|58->43|59->44|61->46|63->48|67->52|67->52|67->52|68->53|68->53|69->54|71->56
                  -- GENERATED --
              */
          