
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
    ProcessCommand(ctx, mapping, urlParameter, ...)

    <h4>Context#request</h4>

    <div>Method: """),_display_(/*12.19*/request/*12.26*/.cmd.ctx.request.method),format.raw/*12.49*/("""</div>
    <div>URI: """),_display_(/*13.16*/request/*13.23*/.cmd.ctx.request.uri),format.raw/*13.43*/("""</div>
    <div>Headers: """),_display_(/*14.20*/request/*14.27*/.cmd.ctx.request.headers),format.raw/*14.51*/("""</div>
    <div>entity: """),_display_(/*15.19*/request/*15.26*/.cmd.ctx.request.entity),format.raw/*15.49*/("""</div>
    <div>protocol: """),_display_(/*16.21*/request/*16.28*/.cmd.ctx.request.protocol),format.raw/*16.53*/("""</div>


    <h4>Mapping</h4>
    """),_display_(/*20.6*/request/*20.13*/.cmd.mapping),format.raw/*20.25*/("""
    """),format.raw/*21.5*/("""<h4>UrlParameter</h4>
    """),_display_(/*22.6*/request/*22.13*/.cmd.urlParameter),format.raw/*22.30*/("""

"""),format.raw/*24.1*/("""</div>


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
                  DATE: Thu Jan 11 18:25:23 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/printRequest.scala.html
                  HASH: 9dc024b740b5f85589ee7a8817e1bc2d26a3b9e7
                  MATRIX: 154->1|509->41|627->64|656->67|811->195|827->202|871->225|920->247|936->254|977->274|1030->300|1046->307|1091->331|1143->356|1159->363|1203->386|1257->413|1273->420|1319->445|1380->480|1396->487|1429->499|1461->504|1514->531|1530->538|1568->555|1597->557
                  LINES: 8->1|13->3|18->3|21->6|27->12|27->12|27->12|28->13|28->13|28->13|29->14|29->14|29->14|30->15|30->15|30->15|31->16|31->16|31->16|35->20|35->20|35->20|36->21|37->22|37->22|37->22|39->24
                  -- GENERATED --
              */
          