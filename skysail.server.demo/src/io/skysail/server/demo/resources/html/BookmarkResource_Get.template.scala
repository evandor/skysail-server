
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.server.RepresentationModel

object BookmarkResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[RepresentationModel,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.28*/("""

"""),_display_(/*8.2*/main/*8.6*/ {_display_(Seq[Any](format.raw/*8.8*/("""

"""),format.raw/*10.1*/("""<br><br><br>

<div class="container">
    <div class="starter-template">
        <h1>Bookmarks</h1>
        <p class="lead">show bookmark:</p>
        <span>"""),_display_(/*16.16*/rep/*16.19*/.rawData),format.raw/*16.27*/("""</span>
        <table class="table table-sm">
            <thead>
            <tr>
                <th>Title</th>
                <th>Url</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row"><input type="text" name="title" value='"""),_display_(/*26.73*/rep/*26.76*/.rawData.head.get("title")),format.raw/*26.102*/("""'/></th>
                <td><input type="url" name="url" value='"""),_display_(/*27.58*/rep/*27.61*/.rawData.head.get("url")),format.raw/*27.85*/("""'/></td>
            </tr>

            <tr>
                <th colspan="2">
                    <a href='#'>Hier: """),_display_(/*32.40*/rep/*32.43*/.rawData.head.get("id")),format.raw/*32.66*/(""" """),format.raw/*32.67*/("""'"""),_display_(/*32.69*/rep/*32.72*/.rawData.head.get("variants")),format.raw/*32.101*/("""'</a>
                </th>
            </tr>

            """),_display_(/*36.14*/for(p <- rep.rawData(0).get("hits")) yield /*36.50*/ {_display_(Seq[Any](format.raw/*36.52*/("""
                """),_display_(/*37.18*/for(q <- p.asInstanceOf[List[Map[_,_]]]) yield /*37.58*/ {_display_(Seq[Any](format.raw/*37.60*/("""
                  """),_display_(/*38.20*/for(key <- q.keys) yield /*38.38*/ {_display_(Seq[Any](format.raw/*38.40*/("""
                  """),format.raw/*39.19*/("""<tr>
                    <th colspan="2">
                      <a href='#'>"""),_display_(/*41.36*/p/*41.37*/.asInstanceOf[List[Map[_,_]]](0).getClass),format.raw/*41.78*/(""" """),format.raw/*41.79*/("""-- """),_display_(/*41.83*/key/*41.86*/.getClass),format.raw/*41.95*/("""</a>
                    </th>
                  </tr>
                  """)))}),format.raw/*44.20*/("""
                """)))}),format.raw/*45.18*/("""
            """)))}),format.raw/*46.14*/("""
            """),format.raw/*47.13*/("""<tr>
                <th colspan="2">
                    <a href='"""),_display_(/*49.31*/rep/*49.34*/.rawData.head.get("id")),format.raw/*49.57*/("""/'>edit bookmark</a>
                </th>
            </tr>
            </tbody>

        </table>

    </div>
</div>

""")))}))
      }
    }
  }

  def render(rep:RepresentationModel): play.twirl.api.HtmlFormat.Appendable = apply(rep)

  def f:((RepresentationModel) => play.twirl.api.HtmlFormat.Appendable) = (rep) => apply(rep)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Fri Dec 29 07:40:25 CET 2017
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/BookmarkResource_Get.scala.html
                  HASH: 06558b4f9964aa79d0715ef56c618b2ccfe97fd5
                  MATRIX: 656->193|777->219|805->222|816->226|854->228|883->230|1068->388|1080->391|1109->399|1428->691|1440->694|1488->720|1581->786|1593->789|1638->813|1782->930|1794->933|1838->956|1867->957|1896->959|1908->962|1959->991|2046->1051|2098->1087|2138->1089|2183->1107|2239->1147|2279->1149|2326->1169|2360->1187|2400->1189|2447->1208|2551->1285|2561->1286|2623->1327|2652->1328|2683->1332|2695->1335|2725->1344|2830->1418|2879->1436|2924->1450|2965->1463|3060->1531|3072->1534|3116->1557
                  LINES: 15->6|20->6|22->8|22->8|22->8|24->10|30->16|30->16|30->16|40->26|40->26|40->26|41->27|41->27|41->27|46->32|46->32|46->32|46->32|46->32|46->32|46->32|50->36|50->36|50->36|51->37|51->37|51->37|52->38|52->38|52->38|53->39|55->41|55->41|55->41|55->41|55->41|55->41|55->41|58->44|59->45|60->46|61->47|63->49|63->49|63->49
                  -- GENERATED --
              */
          