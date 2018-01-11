
package html

import io.skysail.domain.ResponseEventBase

object printResponse extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[ResponseEventBase,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*3.2*/(response: ResponseEventBase):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.31*/("""


"""),format.raw/*6.1*/("""<div>
    <h2>Debug</h2>
    <h3>Response</h3>
    """),_display_(/*9.6*/response),format.raw/*9.14*/("""

"""),format.raw/*11.1*/("""</div>


"""))
      }
    }
  }

  def render(response:ResponseEventBase): play.twirl.api.HtmlFormat.Appendable = apply(response)

  def f:((ResponseEventBase) => play.twirl.api.HtmlFormat.Appendable) = (response) => apply(response)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Thu Jan 11 08:58:44 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/printResponse.scala.html
                  HASH: 8f4a4c5bb43139af845db0c5da111777216d9992
                  MATRIX: 154->1|520->46|644->75|673->78|750->130|778->138|807->140
                  LINES: 8->1|13->3|18->3|21->6|24->9|24->9|26->11
                  -- GENERATED --
              */
          