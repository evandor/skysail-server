
package html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[ResponseEventBase,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(response: ResponseEventBase)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.46*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>
<meta charset="utf-8"/>
<html lang="en">
<head>
    """),_display_(/*6.6*/head()),format.raw/*6.12*/("""
"""),format.raw/*7.1*/("""</head>

<body style="height:100%">

    """),_display_(/*11.6*/navbar()),format.raw/*11.14*/("""

    """),_display_(/*13.6*/content),format.raw/*13.13*/("""

    """),format.raw/*15.5*/("""<!--<custom-element></custom-element>-->


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

</body>
</html>"""))
      }
    }
  }

  def render(response:ResponseEventBase,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(response)(content)

  def f:((ResponseEventBase) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (response) => (content) => apply(response)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Thu Jan 11 08:53:11 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/main.scala.html
                  HASH: d231e77c24e09835e2e3ce90bb2d42d3059fb0a2
                  MATRIX: 466->1|605->45|632->46|726->115|752->121|779->122|847->164|876->172|909->179|937->186|970->192
                  LINES: 12->1|17->1|18->2|22->6|22->6|23->7|27->11|27->11|29->13|29->13|31->15
                  -- GENERATED --
              */
          