
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
        <h1><a href="/demo/v1/bms">Bookmarks</a> &gt; """),_display_(/*19.56*/rep/*19.59*/.rawData.head.get("id")),format.raw/*19.82*/("""</h1>
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
                  DATE: Fri Feb 16 14:52:27 CET 2018
                  SOURCE: C:/git/skysail-apps/skysail.server.app.bookmarks/./src/io/skysail/server/app/bookmarks/resources/BookmarkResource_Get.scala.html
                  HASH: 08b695b883c4d194dcfef008da3895bc468165e2
                  MATRIX: 774->193|960->304|973->310|1067->328|1096->331|1157->301|1186->352|1215->355|1238->369|1278->371|1307->373|1462->501|1474->504|1518->527|1608->590|1620->593|1649->601|1710->635|1722->638|1759->654|1820->688|1832->691|1862->700|1923->734|1935->737|1961->742|2279->1033|2291->1036|2339->1062|2432->1128|2444->1131|2489->1155|2627->1266|2668->1286|2754->1345|2810->1385|2850->1387|2893->1403|2942->1436|2982->1438|3029->1457|3130->1531|3140->1532|3196->1567|3226->1570|3236->1571|3293->1606|3394->1676|3439->1690|3480->1703|3575->1771|3587->1774|3631->1797|3801->1940|3813->1943|3843->1952
                  LINES: 16->6|20->8|20->8|22->8|23->9|26->6|28->11|30->13|30->13|30->13|32->15|36->19|36->19|36->19|38->21|38->21|38->21|40->23|40->23|40->23|42->25|42->25|42->25|44->27|44->27|44->27|54->37|54->37|54->37|55->38|55->38|55->38|60->43|60->43|64->47|64->47|64->47|65->48|65->48|65->48|66->49|68->51|68->51|68->51|68->51|68->51|68->51|71->54|72->55|73->56|75->58|75->58|75->58|83->66|83->66|83->66
                  -- GENERATED --
              */
          