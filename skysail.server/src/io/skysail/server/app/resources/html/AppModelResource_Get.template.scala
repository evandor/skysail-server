
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
        <ul>
            """),_display_(/*13.14*/for(r <- m.resources) yield /*13.35*/ {_display_(Seq[Any](format.raw/*13.37*/("""
                """),format.raw/*14.17*/("""<li><a href="/"""),_display_(/*14.32*/m/*14.33*/.name),format.raw/*14.38*/("""/"""),_display_(/*14.40*/{m.version}),_display_(/*14.52*/r/*14.53*/.path),format.raw/*14.58*/("""">"""),_display_(/*14.61*/r/*14.62*/.path),format.raw/*14.67*/("""</a> -
                    <a target="_blank"
                        href=""""),_display_(/*16.32*/r/*16.33*/.githubPath),format.raw/*16.44*/("""">"""),_display_(/*16.47*/r/*16.48*/.resClass),format.raw/*16.57*/("""</a></li>
            """)))}),format.raw/*17.14*/("""
        """),format.raw/*18.9*/("""</ul>

        <h>Entities</h>
        <ul>
            """),_display_(/*22.14*/for(r <- m.entities) yield /*22.34*/ {_display_(Seq[Any](format.raw/*22.36*/("""
            """),format.raw/*23.13*/("""<li>"""),_display_(/*23.18*/r),format.raw/*23.19*/("""</li>
            """)))}),format.raw/*24.14*/("""
        """),format.raw/*25.9*/("""</ul>
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
                  DATE: Thu Mar 15 18:07:13 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/io/skysail/server/app/resources/AppModelResource_Get.scala.html
                  HASH: 75145535e28272f29ee9f2aefd350be9b7d10ae6
                  MATRIX: 571->1|768->102|796->105|818->119|857->121|885->123|1030->242|1039->243|1064->248|1092->249|1120->251|1129->252|1157->260|1201->278|1210->279|1242->291|1328->350|1365->371|1405->373|1450->390|1492->405|1502->406|1528->411|1557->413|1589->425|1599->426|1625->431|1655->434|1665->435|1691->440|1795->517|1805->518|1837->529|1867->532|1877->533|1907->542|1961->565|1997->574|2081->631|2117->651|2157->653|2198->666|2230->671|2252->672|2302->691|2338->700
                  LINES: 12->1|17->1|19->3|19->3|19->3|21->5|24->8|24->8|24->8|24->8|24->8|24->8|24->8|25->9|25->9|25->9|29->13|29->13|29->13|30->14|30->14|30->14|30->14|30->14|30->14|30->14|30->14|30->14|30->14|30->14|32->16|32->16|32->16|32->16|32->16|32->16|33->17|34->18|38->22|38->22|38->22|39->23|39->23|39->23|40->24|41->25
                  -- GENERATED --
              */
          