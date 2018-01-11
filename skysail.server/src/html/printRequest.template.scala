
package html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel
/*1.2*/import io.skysail.domain.RequestEvent

object printRequest extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[RequestEvent,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*3.2*/(request: RequestEvent):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.25*/("""


"""),format.raw/*6.1*/("""<div>
    <h3>Request</h3>
    ProcessCommand(...)
    <h4>Context</h4>
    """),_display_(/*10.6*/request/*10.13*/.cmd.ctx),format.raw/*10.21*/("""
    """),format.raw/*11.5*/("""<h4>Mapping</h4>
    """),_display_(/*12.6*/request/*12.13*/.cmd.mapping),format.raw/*12.25*/("""

"""),format.raw/*14.1*/("""</div>


"""))
      }
    }
  }

  def render(request:RequestEvent): play.twirl.api.HtmlFormat.Appendable = apply(request)

  def f:((RequestEvent) => play.twirl.api.HtmlFormat.Appendable) = (request) => apply(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Thu Jan 11 09:07:48 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/printRequest.scala.html
                  HASH: 09aa7bff13c8e48fa18a21da4eab14e441e07884
                  MATRIX: 154->1|509->41|627->64|656->67|759->144|775->151|804->159|836->164|884->186|900->193|933->205|962->207
                  LINES: 8->1|13->3|18->3|21->6|25->10|25->10|25->10|26->11|27->12|27->12|27->12|29->14
                  -- GENERATED --
              */
          