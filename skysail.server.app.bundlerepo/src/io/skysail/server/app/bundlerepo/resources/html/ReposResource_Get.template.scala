
package io.skysail.server.app.bundlerepo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.server.RepresentationModel

object ReposResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[RepresentationModel,play.twirl.api.HtmlFormat.Appendable] {

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
        <h1>Repos</h1>
        <p class="lead">available OBR Repositories:</p>
        <table class="table table-sm">
            <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Url</th>
            </tr>
            </thead>
            <tbody>
            """),_display_(/*25.14*/for(p <- rep.rawData) yield /*25.35*/ {_display_(Seq[Any](format.raw/*25.37*/("""
            """),format.raw/*26.13*/("""<tr>
                <th scope="row">"""),_display_(/*27.34*/p/*27.35*/.get("name")),format.raw/*27.47*/("""</th>
                <th scope="row">"""),_display_(/*28.34*/p/*28.35*/.get("url")),format.raw/*28.46*/("""</th>
                <td><a href='"""),_display_(/*29.31*/p/*29.32*/.get("url")),format.raw/*29.43*/("""'>"""),_display_(/*29.46*/p/*29.47*/.get("url")),format.raw/*29.58*/("""</a></td>
            </tr>
            """)))}),format.raw/*31.14*/("""

            """),format.raw/*33.13*/("""</tbody>

        </table>

        <hr>
        <a href="/doc/v1/index.html" target="_docs">Doc</a>
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
                  DATE: Tue Dec 19 09:01:43 CET 2017
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.ext.bundlerepo/./resources/templates/io/skysail/server/ext/bundlerepo/resources/ReposResource_Get.scala.html
                  HASH: d91f76b540505338af7cb2a0d77b732534bb8520
                  MATRIX: 663->193|784->219|812->222|823->226|861->228|890->230|1305->618|1342->639|1382->641|1423->654|1488->692|1498->693|1531->705|1597->744|1607->745|1639->756|1702->792|1712->793|1744->804|1774->807|1784->808|1816->819|1888->860|1930->874
                  LINES: 15->6|20->6|22->8|22->8|22->8|24->10|39->25|39->25|39->25|40->26|41->27|41->27|41->27|42->28|42->28|42->28|43->29|43->29|43->29|43->29|43->29|43->29|45->31|47->33
                  -- GENERATED --
              */
          