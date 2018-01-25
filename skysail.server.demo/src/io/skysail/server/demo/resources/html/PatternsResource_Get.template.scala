
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel
/*6.2*/import html._

object PatternsResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[RepresentationModel,ResponseEventBase,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*8.2*/(rep: RepresentationModel, response: ResponseEventBase):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*8.57*/("""

"""),_display_(/*10.2*/main(response)/*10.16*/ {_display_(Seq[Any](format.raw/*10.18*/("""

"""),format.raw/*12.1*/("""<br><br><br>

<div class="container">
    <div class="starter-template">
    
        <span>
   ***     """),_display_(/*18.13*/rep/*18.16*/.getString("$")),format.raw/*18.31*/("""
        """),format.raw/*19.9*/("""</span>
        <h1>Notes</h1>
        <p class="lead">all notes:</p>
        <table class="table table-sm">
            <thead>
            <tr>
                <th>Id</th>
                <th>From</th>
                <th>To</th>
                <th>Amount</th>
            </tr>
            </thead>
            <tbody>
            """),_display_(/*32.14*/for(p <- rep.rawData) yield /*32.35*/ {_display_(Seq[Any](format.raw/*32.37*/("""
            """),format.raw/*33.13*/("""<tr>
                <th scope="row">"""),_display_(/*34.34*/p/*34.35*/.get("id")),format.raw/*34.45*/("""</th>
                <td>"""),_display_(/*35.22*/p/*35.23*/.get("from").get.getClass.getName),format.raw/*35.56*/("""</td>
                <td>"""),_display_(/*36.22*/p/*36.23*/.get("to").get.getClass.getName),format.raw/*36.54*/("""</td>
                <td>
                    <a href='/demo/v1/patterns/"""),_display_(/*38.49*/p/*38.50*/.get("id")),format.raw/*38.60*/("""'>[show]</a>
                    <a href='/demo/v1/patterns/"""),_display_(/*39.49*/p/*39.50*/.get("id")),format.raw/*39.60*/("""/'>[update]</a>
                </td>
            </tr>
            """)))}),format.raw/*42.14*/("""

            """),format.raw/*44.13*/("""</tbody>

        </table>

        <a href='/demo/v1/patterns/'>Create New Note</a>

        <div>
            <h3>jsonData</h3>
            """),_display_(/*52.14*/rep/*52.17*/.jsonData),format.raw/*52.26*/("""
        """),format.raw/*53.9*/("""</div>

        <div>
            <h3>current Entity</h3>
            <div>"""),_display_(/*57.19*/rep/*57.22*/.entityModel),format.raw/*57.34*/("""</div>
            <h3>current Entity's fields</h3>
            <div>"""),_display_(/*59.19*/Html(rep.entityModel.get.fields.mkString("<br>"))),format.raw/*59.68*/("""</div>
        </div>

        <div>
            """),_display_(/*63.14*/printAppModel(rep.model)/*63.38*/ { whatever =>_display_(Seq[Any](format.raw/*63.52*/(""" """),format.raw/*63.53*/("""... """)))}),format.raw/*63.58*/("""
        """),format.raw/*64.9*/("""</div>

        <div>
            """),_display_(/*67.14*/printRequest(response.req)),format.raw/*67.40*/("""
        """),format.raw/*68.9*/("""</div>


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
                  DATE: Thu Jan 25 09:04:29 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/PatternsResource_Get.scala.html
                  HASH: aa8cd7eb04bac18a331b0da6541713f532378c7e
                  MATRIX: 187->193|551->209|701->264|730->267|753->281|793->283|822->285|954->390|966->393|1002->408|1038->417|1401->753|1438->774|1478->776|1519->789|1584->827|1594->828|1625->838|1679->865|1689->866|1743->899|1797->926|1807->927|1859->958|1961->1033|1971->1034|2002->1044|2090->1105|2100->1106|2131->1116|2231->1185|2273->1199|2443->1342|2455->1345|2485->1354|2521->1363|2624->1439|2636->1442|2669->1454|2766->1524|2836->1573|2913->1623|2946->1647|2998->1661|3027->1662|3063->1667|3099->1676|3161->1711|3208->1737|3244->1746
                  LINES: 8->6|13->8|18->8|20->10|20->10|20->10|22->12|28->18|28->18|28->18|29->19|42->32|42->32|42->32|43->33|44->34|44->34|44->34|45->35|45->35|45->35|46->36|46->36|46->36|48->38|48->38|48->38|49->39|49->39|49->39|52->42|54->44|62->52|62->52|62->52|63->53|67->57|67->57|67->57|69->59|69->59|73->63|73->63|73->63|73->63|73->63|74->64|77->67|77->67|78->68
                  -- GENERATED --
              */
          