
package io.skysail.server.app.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object AppModelResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,io.skysail.server.app.AppModelDescription,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(rep: RepresentationModel, response: ResponseEventBase, m: io.skysail.server.app.AppModelDescription):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.103*/("""

"""),_display_(/*3.2*/main(response)/*3.16*/ {_display_(Seq[Any](format.raw/*3.18*/("""

"""),format.raw/*5.1*/("""<div class="container">
    <div class="starter-template">
        <h1>Application Model</h1>
        <p class="lead">"""),_display_(/*8.26*/m/*8.27*/.name),format.raw/*8.32*/(""" """),format.raw/*8.33*/("""["""),_display_(/*8.35*/m/*8.36*/.version),format.raw/*8.44*/("""]</p>
        <p>"""),_display_(/*9.13*/m/*9.14*/.description),format.raw/*9.26*/("""</p>

        <h4>Resources</h4>

        <table class="table">
            <thead>
            <tr>
                <th scope="col">Url (Pattern)</th>
                <th scope="col">Resource</th>
                <th scope="col">Associated Entity</th>
            </tr>
            </thead>
            <tbody>
            """),_display_(/*22.14*/for(r <- m.resources) yield /*22.35*/ {_display_(Seq[Any](format.raw/*22.37*/("""
            """),format.raw/*23.13*/("""<tr>
                <th scope="row"><a href="/"""),_display_(/*24.44*/m/*24.45*/.name),format.raw/*24.50*/("""/"""),_display_(/*24.52*/{m.version}),_display_(/*24.64*/r/*24.65*/.path),format.raw/*24.70*/("""">"""),_display_(/*24.73*/r/*24.74*/.path),format.raw/*24.79*/("""</a></th>
                <td>"""),_display_(/*25.22*/r/*25.23*/.resClass),format.raw/*25.32*/("""</td>
                <td><a href="_model/"""),_display_(/*26.38*/r/*26.39*/.entityType),format.raw/*26.50*/("""">"""),_display_(/*26.53*/r/*26.54*/.entityType),format.raw/*26.65*/("""</a></td>
            </tr>
            """)))}),format.raw/*28.14*/("""
            """),format.raw/*29.13*/("""</tbody>
        </table>

        <h>Entities</h>
        <ul>
            """),_display_(/*34.14*/for(r <- m.entities) yield /*34.34*/ {_display_(Seq[Any](format.raw/*34.36*/("""
            """),format.raw/*35.13*/("""<li>"""),_display_(/*35.18*/r),format.raw/*35.19*/("""</li>
            """)))}),format.raw/*36.14*/("""
        """),format.raw/*37.9*/("""</ul>
    </div>
</div>

""")))}))
      }
    }
  }

  def render(rep:RepresentationModel,response:ResponseEventBase,m:io.skysail.server.app.AppModelDescription): play.twirl.api.HtmlFormat.Appendable = apply(rep,response,m)

  def f:((RepresentationModel,ResponseEventBase,io.skysail.server.app.AppModelDescription) => play.twirl.api.HtmlFormat.Appendable) = (rep,response,m) => apply(rep,response,m)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Sun Mar 18 08:46:44 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/io/skysail/server/app/resources/AppModelResource_Get.scala.html
                  HASH: 7e2bf0068cedf5163f78d0f68a8e01728a0ccb7a
                  MATRIX: 571->1|768->102|796->105|818->119|857->121|885->123|1030->242|1039->243|1064->248|1092->249|1120->251|1129->252|1157->260|1201->278|1210->279|1242->291|1594->616|1631->637|1671->639|1712->652|1787->700|1797->701|1823->706|1852->708|1884->720|1894->721|1920->726|1950->729|1960->730|1986->735|2044->766|2054->767|2084->776|2154->819|2164->820|2196->831|2226->834|2236->835|2268->846|2340->887|2381->900|2485->977|2521->997|2561->999|2602->1012|2634->1017|2656->1018|2706->1037|2742->1046
                  LINES: 12->1|17->1|19->3|19->3|19->3|21->5|24->8|24->8|24->8|24->8|24->8|24->8|24->8|25->9|25->9|25->9|38->22|38->22|38->22|39->23|40->24|40->24|40->24|40->24|40->24|40->24|40->24|40->24|40->24|40->24|41->25|41->25|41->25|42->26|42->26|42->26|42->26|42->26|42->26|44->28|45->29|50->34|50->34|50->34|51->35|51->35|51->35|52->36|53->37
                  -- GENERATED --
              */
          