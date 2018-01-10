
package html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel
/*1.2*/import io.skysail.domain.model.ApplicationModel

object printAppModel extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[ApplicationModel,_root_.scala.Function1[String, Html],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*3.2*/(model: ApplicationModel)(body: (String) => Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.51*/("""


"""),format.raw/*6.1*/("""<div>
    <h2>Debug</h2>
    <h3>Model</h3>
    """),_display_(/*9.6*/model),format.raw/*9.11*/("""
    """),format.raw/*10.5*/("""<h3>Entities</h3>
    """),_display_(/*11.6*/model/*11.11*/.entities.mkString("<br>")),format.raw/*11.37*/("""

"""),format.raw/*13.1*/("""</div>

<div>
    <h3>todo</h3>
    """),_display_(/*17.6*/model/*17.11*/.entityModelFor("io.skysail.server.demo.domain.Todo")),format.raw/*17.64*/("""
    """),format.raw/*18.5*/("""<h3>fields</h3>
    """),_display_(/*19.6*/model/*19.11*/.entityModelFor("io.skysail.server.demo.domain.Todo").get.fields),format.raw/*19.75*/("""
"""),format.raw/*20.1*/("""</div>


<p class="success">
    """),_display_(/*24.6*/body("green")),format.raw/*24.19*/("""
"""),format.raw/*25.1*/("""</p>


"""))
      }
    }
  }

  def render(model:ApplicationModel,body:_root_.scala.Function1[String, Html]): play.twirl.api.HtmlFormat.Appendable = apply(model)(body)

  def f:((ApplicationModel) => (_root_.scala.Function1[String, Html]) => play.twirl.api.HtmlFormat.Appendable) = (model) => (body) => apply(model)(body)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Wed Jan 10 07:41:07 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/printAppModel.scala.html
                  HASH: ca0e0976250e88a85caa8590a27a2a126c8f6c46
                  MATRIX: 154->1|561->51|705->100|734->103|808->152|833->157|865->162|914->185|928->190|975->216|1004->218|1067->255|1081->260|1155->313|1187->318|1234->339|1248->344|1333->408|1361->409|1421->443|1455->456|1483->457
                  LINES: 8->1|13->3|18->3|21->6|24->9|24->9|25->10|26->11|26->11|26->11|28->13|32->17|32->17|32->17|33->18|34->19|34->19|34->19|35->20|39->24|39->24|40->25
                  -- GENERATED --
              */
          