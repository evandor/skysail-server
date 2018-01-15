
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel
/*6.2*/import html.printAppModel
/*7.2*/import html.printRequest
/*8.2*/import io.skysail.server.demo.domain.Account

object PostPatternResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[RepresentationModel,ResponseEventBase,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*10.2*/(rep: RepresentationModel, response: ResponseEventBase):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*10.57*/("""

"""),_display_(/*12.2*/main(response)/*12.16*/ {_display_(Seq[Any](format.raw/*12.18*/("""

"""),format.raw/*14.1*/("""<br><br><br>

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
                        """),_display_(/*34.26*/for(p <- rep.rawData.head.get("accounts")) yield /*34.68*/ {_display_(Seq[Any](format.raw/*34.70*/("""
                            """),format.raw/*35.29*/("""<option value="1">"""),_display_(/*35.48*/p/*35.49*/.getClass),format.raw/*35.58*/("""</option>
                        """)))}),format.raw/*36.26*/("""
                        """),format.raw/*37.25*/("""</select>
                    </th>
                    <td>
                        <select name="to">
                        """),_display_(/*41.26*/for(p <- rep.rawData.head.get("accounts")) yield /*41.68*/ {_display_(Seq[Any](format.raw/*41.70*/("""
                        """)))}),format.raw/*42.26*/("""
                        """),format.raw/*43.25*/("""</select>
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
            """),_display_(/*59.14*/rep/*59.17*/.jsonData),format.raw/*59.26*/("""
        """),format.raw/*60.9*/("""</div>
        <div>
            <h3>json</h3>
            """),_display_(/*63.14*/rep/*63.17*/.json),format.raw/*63.22*/("""<hr>
            """),_display_(/*64.14*/rep/*64.17*/.json.children),format.raw/*64.31*/("""<hr>
            """),_display_(/*65.14*/rep/*65.17*/.json.values.getClass),format.raw/*65.38*/("""<br>
        </div>

        <div>
            <h3>current Entity</h3>
            <div>"""),_display_(/*70.19*/rep/*70.22*/.entityModel),format.raw/*70.34*/("""</div>
            <h3>current Entity's fields</h3>
            <div>"""),_display_(/*72.19*/Html(rep.entityModel.get.fields.mkString("<br>"))),format.raw/*72.68*/("""</div>
        </div>

        <div>
        """),_display_(/*76.10*/printAppModel(rep.model)/*76.34*/ { whatever =>_display_(Seq[Any](format.raw/*76.48*/(""" """),format.raw/*76.49*/("""... """)))}),format.raw/*76.54*/("""
        """),format.raw/*77.9*/("""</div>

        <div>
        """),_display_(/*80.10*/printRequest(response.req)),format.raw/*80.36*/("""
        """),format.raw/*81.9*/("""</div>

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
                  DATE: Mon Jan 15 17:14:55 CET 2018
                  SOURCE: C:/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/PostPatternResource_Get.scala.html
                  HASH: 57ca20c873f33631fef403f5cd1f4f09323573b3
                  MATRIX: 187->193|220->220|252->246|651->293|802->348|831->351|854->365|894->367|923->369|1532->951|1590->993|1630->995|1687->1024|1733->1043|1743->1044|1773->1053|1839->1088|1892->1113|2048->1242|2106->1284|2146->1286|2203->1312|2256->1337|2688->1742|2700->1745|2730->1754|2766->1763|2853->1823|2865->1826|2891->1831|2936->1849|2948->1852|2983->1866|3028->1884|3040->1887|3082->1908|3198->1997|3210->2000|3243->2012|3340->2082|3410->2131|3483->2177|3516->2201|3568->2215|3597->2216|3633->2221|3669->2230|3727->2261|3774->2287|3810->2296
                  LINES: 8->6|9->7|10->8|15->10|20->10|22->12|22->12|22->12|24->14|44->34|44->34|44->34|45->35|45->35|45->35|45->35|46->36|47->37|51->41|51->41|51->41|52->42|53->43|69->59|69->59|69->59|70->60|73->63|73->63|73->63|74->64|74->64|74->64|75->65|75->65|75->65|80->70|80->70|80->70|82->72|82->72|86->76|86->76|86->76|86->76|86->76|87->77|90->80|90->80|91->81
                  -- GENERATED --
              */
          