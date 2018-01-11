
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
    """),format.raw/*10.5*/("""<h4>Entities</h4>
    """),_display_(/*11.6*/Html(model.entities.mkString("<br>"))),format.raw/*11.43*/("""

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
                  DATE: Thu Jan 11 09:07:48 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/printAppModel.scala.html
                  HASH: abc45691702544308e8a3564a19f5f2fa1933d3e
                  MATRIX: 154->1|561->51|705->100|734->103|808->152|833->157|865->162|914->185|972->222|1001->224|1064->261|1078->266|1152->319|1184->324|1231->345|1245->350|1330->414|1358->415|1418->449|1452->462|1480->463
                  LINES: 8->1|13->3|18->3|21->6|24->9|24->9|25->10|26->11|26->11|28->13|32->17|32->17|32->17|33->18|34->19|34->19|34->19|35->20|39->24|39->24|40->25
                  -- GENERATED --
              */
          