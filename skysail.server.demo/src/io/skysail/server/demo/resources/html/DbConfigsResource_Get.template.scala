
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.server.RepresentationModel

object DbConfigsResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[RepresentationModel,play.twirl.api.HtmlFormat.Appendable] {

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
        <h1>DbConfigs</h1>
        <p class="lead">all keys:</p>
        <table class="table table-sm">
            <thead>
            <tr>
                <th>Key</th>
                <th>Values</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            """),_display_(/*25.14*/for(p <- rep.rawData) yield /*25.35*/ {_display_(Seq[Any](format.raw/*25.37*/("""
            """),format.raw/*26.13*/("""<tr>
                <th scope="row">"""),_display_(/*27.34*/p/*27.35*/.get("key")),format.raw/*27.46*/("""</th>
                <td><a href='"""),_display_(/*28.31*/p/*28.32*/.get("values")),format.raw/*28.46*/("""'>"""),_display_(/*28.49*/p/*28.50*/.get("values")),format.raw/*28.64*/("""</a></td>
                <td><a href='/demo/v1/"""),_display_(/*29.40*/p/*29.41*/.get("id")),format.raw/*29.51*/("""/'>[update]</a></td>
            </tr>
            """)))}),format.raw/*31.14*/("""

            """),format.raw/*33.13*/("""</tbody>

        </table>

        <a href='/demo/v1/dbconfigs/'>Create New Entry!</a>

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
                  DATE: Sat Dec 30 18:50:35 CET 2017
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/DbConfigsResource_Get.scala.html
                  HASH: 8daaa05d498e10a2ecaffc10bee6912f668b180f
                  MATRIX: 657->193|778->219|806->222|817->226|855->228|884->230|1291->610|1328->631|1368->633|1409->646|1474->684|1484->685|1516->696|1579->732|1589->733|1624->747|1654->750|1664->751|1699->765|1775->814|1785->815|1816->825|1899->877|1941->891
                  LINES: 15->6|20->6|22->8|22->8|22->8|24->10|39->25|39->25|39->25|40->26|41->27|41->27|41->27|42->28|42->28|42->28|42->28|42->28|42->28|43->29|43->29|43->29|45->31|47->33
                  -- GENERATED --
              */
          