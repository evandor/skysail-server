
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel
/*6.2*/import html._

object PatternsResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,Object,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*8.2*/(rep: RepresentationModel, response: ResponseEventBase, o: Object):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*8.68*/("""

"""),_display_(/*10.2*/main(response)/*10.16*/ {_display_(Seq[Any](format.raw/*10.18*/("""

"""),format.raw/*12.1*/("""<br><br><br>

<div class="container">
    <div class="starter-template">
    
        <span>
   ***     """),_display_(/*18.13*/rep/*18.16*/.getString("[0]")),format.raw/*18.33*/("""
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
            """),_display_(/*32.14*/for((item, i) <- rep.rawData.zipWithIndex) yield /*32.56*/ {_display_(Seq[Any](format.raw/*32.58*/("""
            """),format.raw/*33.13*/("""<tr>
                <th scope="row">"""),_display_(/*34.34*/rep/*34.37*/.getString(s"[$i].id")),format.raw/*34.59*/("""</th>
                <td>"""),_display_(/*35.22*/rep/*35.25*/.getString(s"[$i].from.title")),format.raw/*35.55*/("""</td>
                <td>"""),_display_(/*36.22*/rep/*36.25*/.getString(s"[$i].to.title")),format.raw/*36.53*/("""</td>
                <td>
                    <a href='/demo/v1/patterns/"""),_display_(/*38.49*/rep/*38.52*/.getString(s"[$i].id")),format.raw/*38.74*/("""'>[show]</a>
                    <a href='/demo/v1/patterns/"""),_display_(/*39.49*/rep/*39.52*/.getString(s"[$i].id")),format.raw/*39.74*/("""/'>[update]</a>
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

  def render(rep:RepresentationModel,response:ResponseEventBase,o:Object): play.twirl.api.HtmlFormat.Appendable = apply(rep,response,o)

  def f:((RepresentationModel,ResponseEventBase,Object) => play.twirl.api.HtmlFormat.Appendable) = (rep,response,o) => apply(rep,response,o)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Mar 12 18:26:54 CET 2018
                  SOURCE: C:/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/PatternsResource_Get.scala.html
                  HASH: 443fd392ad634058767bafca5357fddff5b09e37
                  MATRIX: 187->193|558->209|719->275|748->278|771->292|811->294|840->296|972->401|984->404|1022->421|1058->430|1421->766|1479->808|1519->810|1560->823|1625->861|1637->864|1680->886|1734->913|1746->916|1797->946|1851->973|1863->976|1912->1004|2014->1079|2026->1082|2069->1104|2157->1165|2169->1168|2212->1190|2312->1259|2354->1273|2524->1416|2536->1419|2566->1428|2602->1437|2705->1513|2717->1516|2750->1528|2847->1598|2917->1647|2994->1697|3027->1721|3079->1735|3108->1736|3144->1741|3180->1750|3242->1785|3289->1811|3325->1820
                  LINES: 8->6|13->8|18->8|20->10|20->10|20->10|22->12|28->18|28->18|28->18|29->19|42->32|42->32|42->32|43->33|44->34|44->34|44->34|45->35|45->35|45->35|46->36|46->36|46->36|48->38|48->38|48->38|49->39|49->39|49->39|52->42|54->44|62->52|62->52|62->52|63->53|67->57|67->57|67->57|69->59|69->59|73->63|73->63|73->63|73->63|73->63|74->64|77->67|77->67|78->68
                  -- GENERATED --
              */
          