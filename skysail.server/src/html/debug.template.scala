
package html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object debug extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,_root_.scala.Function1[String, Html],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(level: String = "error")(body: (String) => Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.51*/("""

"""),_display_(/*3.2*/level/*3.7*/ match/*3.13*/ {/*5.1*/case "success" =>/*5.18*/ {_display_(Seq[Any](format.raw/*5.20*/("""
"""),format.raw/*6.1*/("""<p class="success">
    """),_display_(/*7.6*/body("green")),format.raw/*7.19*/("""
"""),format.raw/*8.1*/("""</p>
""")))}/*11.1*/case "warning" =>/*11.18*/ {_display_(Seq[Any](format.raw/*11.20*/("""
"""),format.raw/*12.1*/("""<p class="warning">
    """),_display_(/*13.6*/body("orange")),format.raw/*13.20*/("""
"""),format.raw/*14.1*/("""</p>
""")))}/*17.1*/case "error" =>/*17.16*/ {_display_(Seq[Any](format.raw/*17.18*/("""
"""),format.raw/*18.1*/("""<p class="error">
    """),_display_(/*19.6*/body("red")),format.raw/*19.17*/("""
"""),format.raw/*20.1*/("""</p>
""")))}}),format.raw/*23.2*/("""
"""))
      }
    }
  }

  def render(level:String,body:_root_.scala.Function1[String, Html]): play.twirl.api.HtmlFormat.Appendable = apply(level)(body)

  def f:((String) => (_root_.scala.Function1[String, Html]) => play.twirl.api.HtmlFormat.Appendable) = (level) => (body) => apply(level)(body)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Thu Jan 11 09:41:31 CET 2018
                  SOURCE: C:/git/skysail-server/skysail.server/./src/debug.scala.html
                  HASH: d4df5dfa9310f6aace7fa8b0375c1f617e91df77
                  MATRIX: 488->1|632->50|660->53|672->58|686->64|695->68|720->85|759->87|786->88|836->113|869->126|896->127|920->135|946->152|986->154|1014->155|1065->180|1100->194|1128->195|1152->203|1176->218|1216->220|1244->221|1293->244|1325->255|1353->256|1390->265
                  LINES: 12->1|17->1|19->3|19->3|19->3|19->5|19->5|19->5|20->6|21->7|21->7|22->8|23->11|23->11|23->11|24->12|25->13|25->13|26->14|27->17|27->17|27->17|28->18|29->19|29->19|30->20|31->23
                  -- GENERATED --
              */
          