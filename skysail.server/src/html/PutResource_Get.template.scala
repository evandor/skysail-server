
package html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object PutResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,Object,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel, response: ResponseEventBase, o:Object):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.67*/("""

"""),_display_(/*8.2*/main(response)/*8.16*/ {_display_(Seq[Any](format.raw/*8.18*/("""

"""),format.raw/*10.1*/("""<br><br><br>

<div class="container">
    <div class="starter-template">
        <h1>something</h1>
        <p class="lead">add bookmark:</p>
        <form action='.?_method=PUT' method="POST">

            <table class="table table-sm">
                <thead>
                <tr>
                    """),_display_(/*21.22*/for(f <- rep.entityModel.get.fields.reverse) yield /*21.66*/ {_display_(Seq[Any](format.raw/*21.68*/("""
                    """),format.raw/*22.21*/("""<th>"""),_display_(/*22.26*/f/*22.27*/.name.split("\\.").reverse.head),format.raw/*22.58*/("""</th>
                    """)))}),format.raw/*23.22*/("""
                """),format.raw/*24.17*/("""</tr>
                </thead>
                <tbody>
                <tr>
                    """),_display_(/*28.22*/for(f <- rep.entityModel.get.fields.reverse) yield /*28.66*/ {_display_(Seq[Any](format.raw/*28.68*/("""
                    """),format.raw/*29.21*/("""<td><input type="text" name='"""),_display_(/*29.51*/f/*29.52*/.name.split("\\.").reverse.head),format.raw/*29.83*/("""' value='"""),_display_(/*29.93*/rep/*29.96*/.rawData.head.get(f.name.split("\\.").reverse.head)),format.raw/*29.147*/("""'/></td>
                    """)))}),format.raw/*30.22*/("""
                """),format.raw/*31.17*/("""</tr>
                <tr>
                    <th colspan="2">
                        <input type="submit">
                    </th>
                </tr>
                </tbody>

            </table>
        </form>

        <form action='.?_method=DELETE' method="POST">
            <th colspan="2">
                <input type="submit" value="delete">
            </th>
        </form>

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
                  DATE: Tue Apr 03 20:22:14 CEST 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/PutResource_Get.scala.html
                  HASH: 41fb4e1570d80438cdb2ad0757164b8b845e6351
                  MATRIX: 686->193|846->258|874->261|896->275|935->277|964->279|1295->583|1355->627|1395->629|1444->650|1476->655|1486->656|1538->687|1596->714|1641->731|1765->828|1825->872|1865->874|1914->895|1971->925|1981->926|2033->957|2070->967|2082->970|2155->1021|2216->1051|2261->1068
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|36->21|36->21|36->21|37->22|37->22|37->22|37->22|38->23|39->24|43->28|43->28|43->28|44->29|44->29|44->29|44->29|44->29|44->29|44->29|45->30|46->31
                  -- GENERATED --
              */
          