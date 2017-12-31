
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

def /*8.2*/render/*8.8*/(json: String):play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*8.26*/("""
  """),format.raw/*9.3*/("""render(json)
  "hi"
""")))};
Seq[Any](format.raw/*6.28*/("""

"""),format.raw/*11.2*/("""

"""),_display_(/*13.2*/main/*13.6*/ {_display_(Seq[Any](format.raw/*13.8*/("""

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

  def render(rep:RepresentationModel): play.twirl.api.HtmlFormat.Appendable = apply(rep)

  def f:((RepresentationModel) => play.twirl.api.HtmlFormat.Appendable) = (rep) => apply(rep)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Sat Dec 30 18:50:35 CET 2017
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/BookmarkResource_Get.scala.html
                  HASH: 620e5d070cfb658592e1141db39c3d25873ec894
                  MATRIX: 656->193|760->222|773->228|867->246|896->249|956->219|985->270|1014->273|1026->277|1065->279|1094->281|1249->409|1261->412|1305->435|1395->498|1407->501|1436->509|1497->543|1509->546|1546->562|1607->596|1619->599|1649->608|1710->642|1722->645|1748->650|2066->941|2078->944|2126->970|2219->1036|2231->1039|2276->1063|2414->1174|2455->1194|2541->1253|2597->1293|2637->1295|2680->1311|2729->1344|2769->1346|2816->1365|2917->1439|2927->1440|2983->1475|3013->1478|3023->1479|3080->1514|3181->1584|3226->1598|3267->1611|3362->1679|3374->1682|3418->1705|3588->1848|3600->1851|3630->1860
                  LINES: 15->6|19->8|19->8|21->8|22->9|25->6|27->11|29->13|29->13|29->13|31->15|35->19|35->19|35->19|37->21|37->21|37->21|39->23|39->23|39->23|41->25|41->25|41->25|43->27|43->27|43->27|53->37|53->37|53->37|54->38|54->38|54->38|59->43|59->43|63->47|63->47|63->47|64->48|64->48|64->48|65->49|67->51|67->51|67->51|67->51|67->51|67->51|70->54|71->55|72->56|74->58|74->58|74->58|82->66|82->66|82->66
                  -- GENERATED --
              */
          