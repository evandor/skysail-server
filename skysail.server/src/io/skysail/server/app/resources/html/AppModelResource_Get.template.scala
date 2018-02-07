
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
            """),format.raw/*14.13*/("""<li><a href="/"""),_display_(/*14.28*/m/*14.29*/.name),format.raw/*14.34*/("""/"""),_display_(/*14.36*/{m.version}),_display_(/*14.48*/r/*14.49*/.path),format.raw/*14.54*/("""">"""),_display_(/*14.57*/r/*14.58*/.path),format.raw/*14.63*/("""</a>, """),_display_(/*14.70*/r/*14.71*/.resClass),format.raw/*14.80*/("""</li>
            """)))}),format.raw/*15.14*/("""
        """),format.raw/*16.9*/("""</ul>

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
                  DATE: Wed Feb 07 09:10:07 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/io/skysail/server/app/resources/AppModelResource_Get.scala.html
                  HASH: d47764c6ce7d5f2c25bf073281659aa55864018d
                  MATRIX: 571->1|768->102|796->105|818->119|857->121|885->123|1030->242|1039->243|1064->248|1092->249|1120->251|1129->252|1157->260|1201->278|1210->279|1242->291|1328->350|1365->371|1405->373|1446->386|1488->401|1498->402|1524->407|1553->409|1585->421|1595->422|1621->427|1651->430|1661->431|1687->436|1721->443|1731->444|1761->453|1811->472|1847->481
                  LINES: 12->1|17->1|19->3|19->3|19->3|21->5|24->8|24->8|24->8|24->8|24->8|24->8|24->8|25->9|25->9|25->9|29->13|29->13|29->13|30->14|30->14|30->14|30->14|30->14|30->14|30->14|30->14|30->14|30->14|30->14|30->14|30->14|30->14|31->15|32->16
                  -- GENERATED --
              */
          