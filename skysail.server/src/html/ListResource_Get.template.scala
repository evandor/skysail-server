
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
              """),_display_(/*20.16*/for(f <- rep.entityModel.get.fields.reverse) yield /*20.60*/ {_display_(Seq[Any](format.raw/*20.62*/("""
                """),format.raw/*21.17*/("""<td>"""),_display_(/*21.22*/p/*21.23*/.get(f.name.split("\\.").reverse.head)),format.raw/*21.61*/("""</td>
              """)))}),format.raw/*22.16*/("""
            """),format.raw/*23.13*/("""</tr>
            """)))}),format.raw/*24.14*/("""

            """),format.raw/*26.13*/("""</tbody>

        </table>

        <a href='./'>Create New Entity</a>

        <div>
            <h3>links</h3>
            """),_display_(/*34.14*/Html(rep.response.entity.asInstanceOf[io.skysail.api.ui.Linkable]._links.filter(_.rel == "create-form").headOption.map(_.toHtml()).getOrElse(""))),format.raw/*34.159*/("""
        """),format.raw/*35.9*/("""</div>

        <div>
            <h3>rawData</h3>
            """),_display_(/*39.14*/rep/*39.17*/.rawData),format.raw/*39.25*/("""
        """),format.raw/*40.9*/("""</div>

        <div>
            <h3>jsonData</h3>
            """),_display_(/*44.14*/rep/*44.17*/.jsonData),format.raw/*44.26*/("""
        """),format.raw/*45.9*/("""</div>

        <div>
            <h3>current Entity</h3>
            <div>"""),_display_(/*49.19*/rep/*49.22*/.entityModel),format.raw/*49.34*/("""</div>
            <h3>current Entity's fields</h3>
            <div>"""),_display_(/*51.19*/Html(rep.entityModel.get.fields.mkString("<br>"))),format.raw/*51.68*/("""</div>
        </div>

        <div>
            """),_display_(/*55.14*/printAppModel(rep.model)/*55.38*/ { whatever =>_display_(Seq[Any](format.raw/*55.52*/(""" """),format.raw/*55.53*/("""... """)))}),format.raw/*55.58*/("""
        """),format.raw/*56.9*/("""</div>

        <div>
            """),_display_(/*59.14*/printRequest(response.req)),format.raw/*59.40*/("""
        """),format.raw/*60.9*/("""</div>

        <hr>
        <a href="/doc/v1/index.html" target="_docs">Doc</a>
    </div>
</div>


"""),_display_(/*68.2*/debug("error")/*68.16*/ { color =>_display_(Seq[Any](format.raw/*68.27*/("""
  """),format.raw/*69.3*/("""Oops, something is <span style="color:"""),_display_(/*69.42*/color),format.raw/*69.47*/("""">wrong</span>
""")))}),format.raw/*70.2*/("""

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
                  DATE: Thu Feb 22 08:12:31 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/ListResource_Get.scala.html
                  HASH: 8689ae0098eaa2e1ff0f5e99f0c95cece66aa487
                  MATRIX: 500->1|666->72|694->75|716->89|755->91|783->93|881->165|892->168|923->179|1091->320|1151->364|1191->366|1240->387|1272->392|1282->393|1334->424|1388->447|1429->460|1516->520|1553->541|1593->543|1634->556|1681->576|1741->620|1781->622|1826->639|1858->644|1868->645|1927->683|1979->704|2020->717|2070->736|2112->750|2265->876|2432->1021|2468->1030|2559->1094|2571->1097|2600->1105|2636->1114|2728->1179|2740->1182|2770->1191|2806->1200|2909->1276|2921->1279|2954->1291|3051->1361|3121->1410|3198->1460|3231->1484|3283->1498|3312->1499|3348->1504|3384->1513|3446->1548|3493->1574|3529->1583|3657->1685|3680->1699|3729->1710|3759->1713|3825->1752|3851->1757|3897->1773
                  LINES: 12->1|17->1|19->3|19->3|19->3|21->5|23->7|23->7|23->7|28->12|28->12|28->12|29->13|29->13|29->13|29->13|30->14|31->15|34->18|34->18|34->18|35->19|36->20|36->20|36->20|37->21|37->21|37->21|37->21|38->22|39->23|40->24|42->26|50->34|50->34|51->35|55->39|55->39|55->39|56->40|60->44|60->44|60->44|61->45|65->49|65->49|65->49|67->51|67->51|71->55|71->55|71->55|71->55|71->55|72->56|75->59|75->59|76->60|84->68|84->68|84->68|85->69|85->69|85->69|86->70
                  -- GENERATED --
              */
          