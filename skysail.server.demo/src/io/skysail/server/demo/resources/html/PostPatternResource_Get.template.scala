
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel
/*6.2*/import html.printAppModel
/*7.2*/import html.printRequest
/*8.2*/import io.skysail.server.demo.domain.Account
/*9.2*/import io.skysail.server.demo.domain.Pattern

object PostPatternResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[RepresentationModel,ResponseEventBase,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*11.2*/(rep: RepresentationModel, response: ResponseEventBase):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*11.57*/("""

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

  def render(rep:RepresentationModel,response:ResponseEventBase): play.twirl.api.HtmlFormat.Appendable = apply(rep,response)

  def f:((RepresentationModel,ResponseEventBase) => play.twirl.api.HtmlFormat.Appendable) = (rep,response) => apply(rep,response)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue Jan 16 09:05:00 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/PostPatternResource_Get.scala.html
                  HASH: 33b160984a92a6a9a8a70dfe46daadc151eb4a2e
                  MATRIX: 187->193|220->220|252->246|304->292|703->339|854->394|883->397|906->411|946->413|975->415|1584->997|1660->1057|1700->1059|1757->1088|1800->1104|1810->1105|1838->1112|1868->1115|1878->1116|1905->1122|1971->1157|2024->1182|2184->1315|2260->1375|2300->1377|2357->1406|2400->1422|2410->1423|2438->1430|2468->1433|2478->1434|2505->1440|2575->1479|2628->1504|3060->1909|3072->1912|3102->1921|3138->1930|3225->1990|3237->1993|3263->1998|3308->2016|3320->2019|3362->2040|3473->2124|3485->2127|3531->2152|3576->2170|3588->2173|3670->2234|3786->2323|3798->2326|3831->2338|3928->2408|3998->2457|4071->2503|4104->2527|4156->2541|4185->2542|4221->2547|4257->2556|4315->2587|4362->2613|4398->2622
                  LINES: 8->6|9->7|10->8|11->9|16->11|21->11|23->13|23->13|23->13|25->15|45->35|45->35|45->35|46->36|46->36|46->36|46->36|46->36|46->36|46->36|47->37|48->38|52->42|52->42|52->42|53->43|53->43|53->43|53->43|53->43|53->43|53->43|54->44|55->45|71->61|71->61|71->61|72->62|75->65|75->65|75->65|76->66|76->66|76->66|80->70|80->70|80->70|81->71|81->71|81->71|86->76|86->76|86->76|88->78|88->78|92->82|92->82|92->82|92->82|92->82|93->83|96->86|96->86|97->87
                  -- GENERATED --
              */
          