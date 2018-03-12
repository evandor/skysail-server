
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel
/*6.2*/import html.printAppModel
/*7.2*/import html.printRequest
/*8.2*/import io.skysail.server.demo.domain.Account
/*9.2*/import io.skysail.server.demo.domain.Pattern

object PostPatternResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,Object,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*11.2*/(rep: RepresentationModel, response: ResponseEventBase, o: Object):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*11.68*/("""

"""),_display_(/*13.2*/main(response)/*13.16*/ {_display_(Seq[Any](format.raw/*13.18*/("""

"""),format.raw/*15.1*/("""<br><br><br>

<div class="container">
    <div class="starter-template">
        <h1>Transaction Patterns</h1>
        <p class="lead">add pattern:</p>
        <form action='.' method="post">

            <table class="table table-sm">
                <thead>
                <tr>
                    <th>From</th>
                    <th>To</th>
                    <th>Amount</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row">
                        <select name="from">
                        """),_display_(/*35.26*/for(p <- rep.response.entity.asInstanceOf[Pattern].accounts) yield /*35.86*/ {_display_(Seq[Any](format.raw/*35.88*/("""
                            """),format.raw/*36.29*/("""<option value=""""),_display_(/*36.45*/p/*36.46*/.id.get),format.raw/*36.53*/("""">"""),_display_(/*36.56*/p/*36.57*/.title),format.raw/*36.63*/("""</option>
                        """)))}),format.raw/*37.26*/("""
                        """),format.raw/*38.25*/("""</select>
                    </th>
                    <td>
                        <select name="to">
                            """),_display_(/*42.30*/for(p <- rep.response.entity.asInstanceOf[Pattern].accounts) yield /*42.90*/ {_display_(Seq[Any](format.raw/*42.92*/("""
                            """),format.raw/*43.29*/("""<option value=""""),_display_(/*43.45*/p/*43.46*/.id.get),format.raw/*43.53*/("""">"""),_display_(/*43.56*/p/*43.57*/.title),format.raw/*43.63*/("""</option>
                            """)))}),format.raw/*44.30*/("""
                        """),format.raw/*45.25*/("""</select>
                    </td>
                    <td><input type="text" name="amount" value="0"/></td>
                </tr>
                <tr>
                    <th colspan="2">
                        <input type="submit">
                    </th>
                </tr>
                </tbody>

            </table>
        </form>

        <div>
            <h3>jsonData</h3>
            """),_display_(/*61.14*/rep/*61.17*/.jsonData),format.raw/*61.26*/("""
        """),format.raw/*62.9*/("""</div>
        <div>
            <h3>json</h3>
            """),_display_(/*65.14*/rep/*65.17*/.json),format.raw/*65.22*/("""<hr>
            """),_display_(/*66.14*/rep/*66.17*/.json.values.getClass),format.raw/*66.38*/("""<hr>
        </div>
        <div>
            <h3>response.entity</h3>
            """),_display_(/*70.14*/rep/*70.17*/.response.entity.getClass),format.raw/*70.42*/("""<hr>
            """),_display_(/*71.14*/rep/*71.17*/.response.entity.asInstanceOf[Pattern].accounts.head.getClass),format.raw/*71.78*/("""<hr>
        </div>

        <div>
            <h3>current Entity</h3>
            <div>"""),_display_(/*76.19*/rep/*76.22*/.entityModel),format.raw/*76.34*/("""</div>
            <h3>current Entity's fields</h3>
            <div>"""),_display_(/*78.19*/Html(rep.entityModel.get.fields.mkString("<br>"))),format.raw/*78.68*/("""</div>
        </div>

        <div>
        """),_display_(/*82.10*/printAppModel(rep.model)/*82.34*/ { whatever =>_display_(Seq[Any](format.raw/*82.48*/(""" """),format.raw/*82.49*/("""... """)))}),format.raw/*82.54*/("""
        """),format.raw/*83.9*/("""</div>

        <div>
        """),_display_(/*86.10*/printRequest(response.req)),format.raw/*86.36*/("""
        """),format.raw/*87.9*/("""</div>

    </div>
</div>

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
                  DATE: Mon Mar 12 18:26:54 CET 2018
                  SOURCE: C:/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/PostPatternResource_Get.scala.html
                  HASH: 79bb675d8831e3b86750cbd431d3379c33fba6d4
                  MATRIX: 187->193|220->220|252->246|304->292|710->339|872->405|901->408|924->422|964->424|993->426|1602->1008|1678->1068|1718->1070|1775->1099|1818->1115|1828->1116|1856->1123|1886->1126|1896->1127|1923->1133|1989->1168|2042->1193|2202->1326|2278->1386|2318->1388|2375->1417|2418->1433|2428->1434|2456->1441|2486->1444|2496->1445|2523->1451|2593->1490|2646->1515|3078->1920|3090->1923|3120->1932|3156->1941|3243->2001|3255->2004|3281->2009|3326->2027|3338->2030|3380->2051|3491->2135|3503->2138|3549->2163|3594->2181|3606->2184|3688->2245|3804->2334|3816->2337|3849->2349|3946->2419|4016->2468|4089->2514|4122->2538|4174->2552|4203->2553|4239->2558|4275->2567|4333->2598|4380->2624|4416->2633
                  LINES: 8->6|9->7|10->8|11->9|16->11|21->11|23->13|23->13|23->13|25->15|45->35|45->35|45->35|46->36|46->36|46->36|46->36|46->36|46->36|46->36|47->37|48->38|52->42|52->42|52->42|53->43|53->43|53->43|53->43|53->43|53->43|53->43|54->44|55->45|71->61|71->61|71->61|72->62|75->65|75->65|75->65|76->66|76->66|76->66|80->70|80->70|80->70|81->71|81->71|81->71|86->76|86->76|86->76|88->78|88->78|92->82|92->82|92->82|92->82|92->82|93->83|96->86|96->86|97->87
                  -- GENERATED --
              */
          