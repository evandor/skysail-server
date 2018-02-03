
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object BookmarkResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,Object,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel, response: ResponseEventBase, o: Object):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {

def /*8.2*/render/*8.8*/(json: String):play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*8.26*/("""
  """),format.raw/*9.3*/("""render(json)
  "hi"
""")))};
Seq[Any](format.raw/*6.68*/("""

"""),format.raw/*11.2*/("""

"""),_display_(/*13.2*/main(response)/*13.16*/ {_display_(Seq[Any](format.raw/*13.18*/("""

"""),format.raw/*15.1*/("""<br><br><br>

<div class="container">
    <div class="starter-template">
        <h1><a href="/demo/v1/bms">Bookmarks</a> &gt; """),_display_(/*19.56*/rep/*19.59*/.rawData.head.get("id")),format.raw/*19.82*/("""</h1>
        <p class="lead">show bookmark:</p>
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
                <th>Url</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row"><input type="text" name="title" value='"""),_display_(/*37.73*/rep/*37.76*/.rawData.head.get("title")),format.raw/*37.102*/("""'/></th>
                <td><input type="url" name="url" value='"""),_display_(/*38.58*/rep/*38.61*/.rawData.head.get("url")),format.raw/*38.85*/("""'/></td>
            </tr>

            <tr>
                <th colspan="2">
                    <a href='#'>"""),_display_(/*43.34*/render(rep.jsonData)),format.raw/*43.54*/("""</a>
                </th>
            </tr>

            """),_display_(/*47.14*/for(p <- rep.rawData(0).get("variants")) yield /*47.54*/ {_display_(Seq[Any](format.raw/*47.56*/("""
              """),_display_(/*48.16*/for(q <- p.asInstanceOf[List[_]]) yield /*48.49*/ {_display_(Seq[Any](format.raw/*48.51*/("""
                  """),format.raw/*49.19*/("""<tr>
                    <th colspan="2">
                      <a href='"""),_display_(/*51.33*/q/*51.34*/.asInstanceOf[Map[String,_]]("url")),format.raw/*51.69*/("""'>"""),_display_(/*51.72*/q/*51.73*/.asInstanceOf[Map[String,_]]("url")),format.raw/*51.108*/("""</a>
                    </th>
                  </tr>
              """)))}),format.raw/*54.16*/("""
            """)))}),format.raw/*55.14*/("""
            """),format.raw/*56.13*/("""<tr>
                <th colspan="2">
                    <a href='"""),_display_(/*58.31*/rep/*58.34*/.rawData.head.get("id")),format.raw/*58.57*/("""/'>edit bookmark</a>
                </th>
            </tr>
            </tbody>

        </table>

        yyyyy
        <dom-element json=""""),_display_(/*66.29*/rep/*66.32*/.jsonData),format.raw/*66.41*/(""""></dom-element>


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
                  DATE: Sat Feb 03 09:40:24 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/BookmarkResource_Get.scala.html
                  HASH: fa125d3286df4a444f0f0ad582e4fdd064ef104f
                  MATRIX: 724->193|868->262|881->268|975->286|1004->289|1064->259|1093->310|1122->313|1145->327|1185->329|1214->331|1369->459|1381->462|1425->485|1515->548|1527->551|1556->559|1617->593|1629->596|1666->612|1727->646|1739->649|1769->658|1830->692|1842->695|1868->700|2186->991|2198->994|2246->1020|2339->1086|2351->1089|2396->1113|2534->1224|2575->1244|2661->1303|2717->1343|2757->1345|2800->1361|2849->1394|2889->1396|2936->1415|3037->1489|3047->1490|3103->1525|3133->1528|3143->1529|3200->1564|3301->1634|3346->1648|3387->1661|3482->1729|3494->1732|3538->1755|3708->1898|3720->1901|3750->1910
                  LINES: 16->6|20->8|20->8|22->8|23->9|26->6|28->11|30->13|30->13|30->13|32->15|36->19|36->19|36->19|38->21|38->21|38->21|40->23|40->23|40->23|42->25|42->25|42->25|44->27|44->27|44->27|54->37|54->37|54->37|55->38|55->38|55->38|60->43|60->43|64->47|64->47|64->47|65->48|65->48|65->48|66->49|68->51|68->51|68->51|68->51|68->51|68->51|71->54|72->55|73->56|75->58|75->58|75->58|83->66|83->66|83->66
                  -- GENERATED --
              */
          