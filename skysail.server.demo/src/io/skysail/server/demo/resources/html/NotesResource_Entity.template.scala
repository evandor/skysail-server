
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.server.RepresentationModel

object NotesResource_Entity extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[RepresentationModel,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {

def /*8.2*/render/*8.8*/(json: String):play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*8.26*/("""
  """),format.raw/*9.3*/("""render(json)
  "hi"
""")))};
Seq[Any](format.raw/*6.28*/("""

"""),format.raw/*11.2*/("""

"""),_display_(/*13.2*/main/*13.6*/ {_display_(Seq[Any](format.raw/*13.8*/("""

"""),format.raw/*15.1*/("""<br><br><br>

<div class="container">
    <div class="starter-template">
        <h1><a href="/demo/v1/notes">Notes</a> &gt; """),_display_(/*19.54*/rep/*19.57*/.rawData.head.get("id")),format.raw/*19.80*/("""</h1>
        <p class="lead">show note:</p>
        <div>"""),_display_(/*21.15*/rep/*21.18*/.rawData),format.raw/*21.26*/("""</div>
        <hr>
        <div>"""),_display_(/*23.15*/rep/*23.18*/.response.entity),format.raw/*23.34*/("""</div>
        <hr>
        <div>"""),_display_(/*25.15*/rep/*25.18*/.jsonData),format.raw/*25.27*/("""</div>
        <hr>
        <div>"""),_display_(/*27.15*/rep/*27.18*/.json),format.raw/*27.23*/("""</div>
        <table class="table table-sm">
            <thead>
            <tr>
                <th>Title</th>
                <th>Content</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">"""),_display_(/*37.34*/rep/*37.37*/.rawData.head.get("title")),format.raw/*37.63*/("""</th>
                <td>"""),_display_(/*38.22*/rep/*38.25*/.rawData.head.get("content")),format.raw/*38.53*/("""</td>
            </tr>

            <tr>
                <th colspan="2">
                    <a href='#'>"""),_display_(/*43.34*/render(rep.jsonData)),format.raw/*43.54*/("""</a>
                </th>
            </tr>

            
            <tr>
                <th colspan="2">
                    <a href='"""),_display_(/*50.31*/rep/*50.34*/.rawData.head.get("id")),format.raw/*50.57*/("""/'>edit note</a>
                </th>
            </tr>
            </tbody>

        </table>

        yyyyy
        <dom-element json=""""),_display_(/*58.29*/rep/*58.32*/.jsonData),format.raw/*58.41*/(""""></dom-element>


    </div>
</div>

""")))}))
      }
    }
  }

  def render(rep:RepresentationModel): play.twirl.api.HtmlFormat.Appendable = apply(rep)

  def f:((RepresentationModel) => play.twirl.api.HtmlFormat.Appendable) = (rep) => apply(rep)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Wed Jan 03 17:59:39 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/NotesResource_Entity.scala.html
                  HASH: d72010c6e38d294819c1ff347c666d270793f1c9
                  MATRIX: 656->193|760->222|773->228|867->246|896->249|956->219|985->270|1014->273|1026->277|1065->279|1094->281|1247->407|1259->410|1303->433|1389->492|1401->495|1430->503|1491->537|1503->540|1540->556|1601->590|1613->593|1643->602|1704->636|1716->639|1742->644|2025->900|2037->903|2084->929|2138->956|2150->959|2199->987|2334->1095|2375->1115|2541->1254|2553->1257|2597->1280|2763->1419|2775->1422|2805->1431
                  LINES: 15->6|19->8|19->8|21->8|22->9|25->6|27->11|29->13|29->13|29->13|31->15|35->19|35->19|35->19|37->21|37->21|37->21|39->23|39->23|39->23|41->25|41->25|41->25|43->27|43->27|43->27|53->37|53->37|53->37|54->38|54->38|54->38|59->43|59->43|66->50|66->50|66->50|74->58|74->58|74->58
                  -- GENERATED --
              */
          