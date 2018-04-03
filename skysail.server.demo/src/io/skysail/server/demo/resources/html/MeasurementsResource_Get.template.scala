
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object MeasurementsResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,Object,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel, response: ResponseEventBase, o: Object):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.68*/("""

"""),_display_(/*8.2*/main(response)/*8.16*/ {_display_(Seq[Any](format.raw/*8.18*/("""

    """),format.raw/*10.5*/("""<script src="http://benpickles.github.io/peity/jquery.peity.min.js"></script>
    <span class="line">"""),_display_(/*11.25*/rep/*11.28*/.rawData.map(_.get("duration").get).mkString(",")),format.raw/*11.77*/("""</span>
    <script>
        $.fn.peity.defaults.line = """),format.raw/*13.36*/("""{"""),format.raw/*13.37*/("""
          """),format.raw/*14.11*/("""fill: "#c6d9fd",
          max: null,
          min: 0,
          stroke: "#4d89f9",
          strokeWidth: 1,
          width: 320,
          height:320
        """),format.raw/*21.9*/("""}"""),format.raw/*21.10*/("""
        """),format.raw/*22.9*/("""$(".line").peity("line")
    </script>

""")))}))
      }
    }
  }

  def render(rep:RepresentationModel,response:ResponseEventBase,o:Object): play.twirl.api.HtmlFormat.Appendable = apply(rep,response,o)

  def f:((RepresentationModel,ResponseEventBase,Object) => play.twirl.api.HtmlFormat.Appendable) = (rep,response,o) => apply(rep,response,o)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Sat Mar 31 15:57:11 CEST 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/MeasurementsResource_Get.scala.html
                  HASH: 9e15df4d64d8edeb1481458c68188510280d9692
                  MATRIX: 728->193|889->259|917->262|939->276|978->278|1011->284|1140->386|1152->389|1222->438|1306->494|1335->495|1374->506|1563->668|1592->669|1628->678
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|26->11|26->11|26->11|28->13|28->13|29->14|36->21|36->21|37->22
                  -- GENERATED --
              */
          