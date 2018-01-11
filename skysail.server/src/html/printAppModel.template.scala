
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
    """),_display_(/*10.6*/model),format.raw/*10.11*/("""

    """),format.raw/*12.5*/("""<h4>Resources</h4>
    """),_display_(/*13.6*/Html(model.resourceModels.mkString("<br>"))),format.raw/*13.49*/("""

    """),format.raw/*15.5*/("""<h4>Entities</h4>
    """),_display_(/*16.6*/Html(model.entities.mkString("<br>"))),format.raw/*16.43*/("""

"""),format.raw/*18.1*/("""</div>

<div>
    <h3>todo</h3>
    """),_display_(/*22.6*/model/*22.11*/.entityModelFor("io.skysail.server.demo.domain.Todo")),format.raw/*22.64*/("""
    """),format.raw/*23.5*/("""<h3>fields</h3>
    """),_display_(/*24.6*/model/*24.11*/.entityModelFor("io.skysail.server.demo.domain.Todo").get.fields),format.raw/*24.75*/("""
"""),format.raw/*25.1*/("""</div>


<p class="success">
    """),_display_(/*29.6*/body("green")),format.raw/*29.19*/("""
"""),format.raw/*30.1*/("""</p>


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
                  DATE: Thu Jan 11 13:39:30 CET 2018
                  SOURCE: C:/git/skysail-server/skysail.server/./src/printAppModel.scala.html
                  HASH: 1f01a762d8da9137ebcd38089db3abac0a63a21c
                  MATRIX: 154->1|561->51|705->100|734->103|810->153|836->158|869->164|919->188|983->231|1016->237|1065->260|1123->297|1152->299|1215->336|1229->341|1303->394|1335->399|1382->420|1396->425|1481->489|1509->490|1569->524|1603->537|1631->538
                  LINES: 8->1|13->3|18->3|21->6|25->10|25->10|27->12|28->13|28->13|30->15|31->16|31->16|33->18|37->22|37->22|37->22|38->23|39->24|39->24|39->24|40->25|44->29|44->29|45->30
                  -- GENERATED --
              */
          