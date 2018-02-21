
package html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object ListResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,AnyRef,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(rep: RepresentationModel, response: ResponseEventBase, entity: AnyRef):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.73*/("""

"""),_display_(/*3.2*/main(response)/*3.16*/ {_display_(Seq[Any](format.raw/*3.18*/("""

"""),format.raw/*5.1*/("""<div class="container">
    <div class="starter-template">
        <h1>"""),_display_(/*7.14*/rep/*7.17*/.model.name),format.raw/*7.28*/("""</h1>
        <p class="lead">all entities:</p>
        <table class="table table-sm">
            <thead>
            <tr>
                """),_display_(/*12.18*/for(f <- rep.entityModel.get.fields.reverse) yield /*12.62*/ {_display_(Seq[Any](format.raw/*12.64*/("""
                    """),format.raw/*13.21*/("""<th>"""),_display_(/*13.26*/f/*13.27*/.name.split("\\.").reverse.head),format.raw/*13.58*/("""</th>
                """)))}),format.raw/*14.18*/("""
            """),format.raw/*15.13*/("""</tr>
            </thead>
            <tbody>
            """),_display_(/*18.14*/for(p <- rep.rawData) yield /*18.35*/ {_display_(Seq[Any](format.raw/*18.37*/("""
            """),format.raw/*19.13*/("""<tr>
                <th scope="row">"""),_display_(/*20.34*/p/*20.35*/.get("title")),format.raw/*20.48*/("""</th>
                <td><a href='"""),_display_(/*21.31*/p/*21.32*/.get("url")),format.raw/*21.43*/("""' target='demo_bms_"""),_display_(/*21.63*/p/*21.64*/.get("title")),format.raw/*21.77*/("""'>"""),_display_(/*21.80*/p/*21.81*/.get("url")),format.raw/*21.92*/("""</a></td>
                <td>
                    <a href='./"""),_display_(/*23.33*/p/*23.34*/.get("id")),format.raw/*23.44*/("""'>[show]</a>
                    <a href='./"""),_display_(/*24.33*/p/*24.34*/.get("id")),format.raw/*24.44*/("""/'>[update]</a>
                </td>
            </tr>
            """)))}),format.raw/*27.14*/("""

            """),format.raw/*29.13*/("""</tbody>

        </table>

        <a href='./'>Create New Entity</a>

        <div>
            <h3>links</h3>
            """),_display_(/*37.14*/rep/*37.17*/.response.entity.asInstanceOf[io.skysail.api.ui.Linkable]._links.filter(_.rel == "create-form").toHtml),format.raw/*37.119*/("""
        """),format.raw/*38.9*/("""</div>

        <div>
            <h3>jsonData</h3>
            """),_display_(/*42.14*/rep/*42.17*/.jsonData),format.raw/*42.26*/("""
        """),format.raw/*43.9*/("""</div>

        <div>
            <h3>current Entity</h3>
            <div>"""),_display_(/*47.19*/rep/*47.22*/.entityModel),format.raw/*47.34*/("""</div>
            <h3>current Entity's fields</h3>
            <div>"""),_display_(/*49.19*/Html(rep.entityModel.get.fields.mkString("<br>"))),format.raw/*49.68*/("""</div>
        </div>

        <div>
            """),_display_(/*53.14*/printAppModel(rep.model)/*53.38*/ { whatever =>_display_(Seq[Any](format.raw/*53.52*/(""" """),format.raw/*53.53*/("""... """)))}),format.raw/*53.58*/("""
        """),format.raw/*54.9*/("""</div>

        <div>
            """),_display_(/*57.14*/printRequest(response.req)),format.raw/*57.40*/("""
        """),format.raw/*58.9*/("""</div>

        <hr>
        <a href="/doc/v1/index.html" target="_docs">Doc</a>
    </div>
</div>


"""),_display_(/*66.2*/debug("error")/*66.16*/ { color =>_display_(Seq[Any](format.raw/*66.27*/("""
  """),format.raw/*67.3*/("""Oops, something is <span style="color:"""),_display_(/*67.42*/color),format.raw/*67.47*/("""">wrong</span>
""")))}),format.raw/*68.2*/("""

""")))}))
      }
    }
  }

  def render(rep:RepresentationModel,response:ResponseEventBase,entity:AnyRef): play.twirl.api.HtmlFormat.Appendable = apply(rep,response,entity)

  def f:((RepresentationModel,ResponseEventBase,AnyRef) => play.twirl.api.HtmlFormat.Appendable) = (rep,response,entity) => apply(rep,response,entity)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Wed Feb 21 17:15:46 CET 2018
                  SOURCE: C:/git/skysail-server/skysail.server/./src/ListResource_Get.scala.html
                  HASH: 3662ef7a9ee214c5324d89b6421615d59d32b47d
                  MATRIX: 500->1|666->72|694->75|716->89|755->91|783->93|881->165|892->168|923->179|1091->320|1151->364|1191->366|1240->387|1272->392|1282->393|1334->424|1388->447|1429->460|1516->520|1553->541|1593->543|1634->556|1699->594|1709->595|1743->608|1806->644|1816->645|1848->656|1895->676|1905->677|1939->690|1969->693|1979->694|2011->705|2101->768|2111->769|2142->779|2214->824|2224->825|2255->835|2355->904|2397->918|2550->1044|2562->1047|2686->1149|2722->1158|2814->1223|2826->1226|2856->1235|2892->1244|2995->1320|3007->1323|3040->1335|3137->1405|3207->1454|3284->1504|3317->1528|3369->1542|3398->1543|3434->1548|3470->1557|3532->1592|3579->1618|3615->1627|3743->1729|3766->1743|3815->1754|3845->1757|3911->1796|3937->1801|3983->1817
                  LINES: 12->1|17->1|19->3|19->3|19->3|21->5|23->7|23->7|23->7|28->12|28->12|28->12|29->13|29->13|29->13|29->13|30->14|31->15|34->18|34->18|34->18|35->19|36->20|36->20|36->20|37->21|37->21|37->21|37->21|37->21|37->21|37->21|37->21|37->21|39->23|39->23|39->23|40->24|40->24|40->24|43->27|45->29|53->37|53->37|53->37|54->38|58->42|58->42|58->42|59->43|63->47|63->47|63->47|65->49|65->49|69->53|69->53|69->53|69->53|69->53|70->54|73->57|73->57|74->58|82->66|82->66|82->66|83->67|83->67|83->67|84->68
                  -- GENERATED --
              */
          