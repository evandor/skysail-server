
package io.skysail.server.app.bookmarks.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object BookmarkResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,io.skysail.server.app.bookmarks.domain.Bookmark,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel, response: ResponseEventBase, bm: io.skysail.server.app.bookmarks.domain.Bookmark):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {

def /*8.2*/render/*8.8*/(json: String):play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*8.26*/("""
  """),format.raw/*9.3*/("""render(json)
  "hi"
""")))};
Seq[Any](format.raw/*6.110*/("""

"""),format.raw/*11.2*/("""

"""),_display_(/*13.2*/main(response)/*13.16*/ {_display_(Seq[Any](format.raw/*13.18*/("""

"""),format.raw/*15.1*/("""<br><br><br>

<div class="container">
    <div class="starter-template">
        <h1><a href="/bookmarks/v1/bms">Bookmarks</a> &gt; """),_display_(/*19.61*/rep/*19.64*/.rawData.head.get("id")),format.raw/*19.87*/("""</h1>
        <p class="lead">show bookmark:</p>
        <div>"""),_display_(/*21.15*/rep/*21.18*/.rawData),format.raw/*21.26*/("""</div>
        <hr>
        <div>"""),_display_(/*23.15*/rep/*23.18*/.response.entity),format.raw/*23.34*/("""</div>
        <hr>
        <div>"""),_display_(/*25.15*/rep/*25.18*/.jsonData),format.raw/*25.27*/("""</div>
        <hr>
        <div>"""),_display_(/*27.15*/rep/*27.18*/.json),format.raw/*27.23*/("""</div>
        <table class="table table-sm">
            <thead>
            <tr>
                <th>Title</th>
                <th>Url</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row"><input type="text" name="title" value='"""),_display_(/*37.73*/rep/*37.76*/.rawData.head.get("title")),format.raw/*37.102*/("""'/></th>
                <td><input type="url" name="url" value='"""),_display_(/*38.58*/rep/*38.61*/.rawData.head.get("url")),format.raw/*38.85*/("""'/></td>
            </tr>

            <tr>
                <th colspan="2">
                    <a href='#'>"""),_display_(/*43.34*/render(rep.jsonData)),format.raw/*43.54*/("""</a>
                </th>
            </tr>

            """),_display_(/*47.14*/for(p <- rep.rawData(0).get("variants")) yield /*47.54*/ {_display_(Seq[Any](format.raw/*47.56*/("""
              """),_display_(/*48.16*/for(q <- p.asInstanceOf[List[_]]) yield /*48.49*/ {_display_(Seq[Any](format.raw/*48.51*/("""
                  """),format.raw/*49.19*/("""<tr>
                    <th colspan="2">
                      <a href='"""),_display_(/*51.33*/q/*51.34*/.asInstanceOf[Map[String,_]]("url")),format.raw/*51.69*/("""'>"""),_display_(/*51.72*/q/*51.73*/.asInstanceOf[Map[String,_]]("url")),format.raw/*51.108*/("""</a>
                    </th>
                  </tr>
              """)))}),format.raw/*54.16*/("""
            """)))}),format.raw/*55.14*/("""
            """),format.raw/*56.13*/("""<tr>
                <th colspan="2">
                    <a href='"""),_display_(/*58.31*/rep/*58.34*/.rawData.head.get("id")),format.raw/*58.57*/("""/'>edit bookmark</a>
                </th>
            </tr>
            </tbody>

        </table>

        yyyyy
        <dom-element json=""""),_display_(/*66.29*/rep/*66.32*/.jsonData),format.raw/*66.41*/(""""></dom-element>


    </div>
</div>

""")))}))
      }
    }
  }

  def render(rep:RepresentationModel,response:ResponseEventBase,bm:io.skysail.server.app.bookmarks.domain.Bookmark): play.twirl.api.HtmlFormat.Appendable = apply(rep,response,bm)

  def f:((RepresentationModel,ResponseEventBase,io.skysail.server.app.bookmarks.domain.Bookmark) => play.twirl.api.HtmlFormat.Appendable) = (rep,response,bm) => apply(rep,response,bm)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Sun Feb 18 12:38:14 CET 2018
                  SOURCE: /Users/carsten/git/skysail-apps/skysail.server.app.bookmarks/./src/io/skysail/server/app/bookmarks/resources/BookmarkResource_Get.scala.html
                  HASH: 52fdb70f8e18876d16d594b7935a292a12ba5d02
                  MATRIX: 774->193|960->304|973->310|1067->328|1096->331|1157->301|1186->352|1215->355|1238->369|1278->371|1307->373|1467->506|1479->509|1523->532|1613->595|1625->598|1654->606|1715->640|1727->643|1764->659|1825->693|1837->696|1867->705|1928->739|1940->742|1966->747|2284->1038|2296->1041|2344->1067|2437->1133|2449->1136|2494->1160|2632->1271|2673->1291|2759->1350|2815->1390|2855->1392|2898->1408|2947->1441|2987->1443|3034->1462|3135->1536|3145->1537|3201->1572|3231->1575|3241->1576|3298->1611|3399->1681|3444->1695|3485->1708|3580->1776|3592->1779|3636->1802|3806->1945|3818->1948|3848->1957
                  LINES: 16->6|20->8|20->8|22->8|23->9|26->6|28->11|30->13|30->13|30->13|32->15|36->19|36->19|36->19|38->21|38->21|38->21|40->23|40->23|40->23|42->25|42->25|42->25|44->27|44->27|44->27|54->37|54->37|54->37|55->38|55->38|55->38|60->43|60->43|64->47|64->47|64->47|65->48|65->48|65->48|66->49|68->51|68->51|68->51|68->51|68->51|68->51|71->54|72->55|73->56|75->58|75->58|75->58|83->66|83->66|83->66
                  -- GENERATED --
              */
          