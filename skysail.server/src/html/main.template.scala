
package html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[ResponseEventBase,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(response: ResponseEventBase)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.46*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>
<meta charset="utf-8"/>
<html lang="en">
<head>
    """),_display_(/*6.6*/head()),format.raw/*6.12*/("""
"""),format.raw/*7.1*/("""</head>

<body style="height:100%">

    """),_display_(/*11.6*/navbar()),format.raw/*11.14*/("""

    """),format.raw/*13.5*/("""<div class="container-fluid">
        <div class="row">
            <nav class="col-md-2 d-none d-md-block bg-light sidebar">
                <div class="sidebar-sticky">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link active" href="#">
                                <span data-feather="home"></span>
                                Applications <span class="sr-only">(current)</span>
                                <div id="appnav"></div>
                                <div id="menunav"></div>
                            </a>
                        </li>
                    </ul>

                    <!--<h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span>Saved reports</span>
                        <a class="d-flex align-items-center text-muted" href="#">
                            <span data-feather="plus-circle"></span>
                        </a>
                    </h6>
                    <ul class="nav flex-column mb-2">
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <span data-feather="file-text"></span>
                                Current month
                            </a>
                        </li>

                    </ul>-->
                </div>
            </nav>

            <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
                """),_display_(/*47.18*/content),format.raw/*47.25*/("""
            """),format.raw/*48.13*/("""</main>
        </div>
    </div>

    <script>

      function createElement(i, name,context) """),format.raw/*54.47*/("""{"""),format.raw/*54.48*/("""
        """),format.raw/*55.9*/("""return $('<div>', """),format.raw/*55.27*/("""{"""),format.raw/*55.28*/("""
          """),format.raw/*56.11*/("""html: $('<a>', """),format.raw/*56.26*/("""{"""),format.raw/*56.27*/("""
            """),format.raw/*57.13*/("""href: context,
            text: name
          """),format.raw/*59.11*/("""}"""),format.raw/*59.12*/(""")
        """),format.raw/*60.9*/("""}"""),format.raw/*60.10*/(""")
      """),format.raw/*61.7*/("""}"""),format.raw/*61.8*/("""

      """),format.raw/*63.7*/("""$.get( "/_root/apps", function( data ) """),format.raw/*63.46*/("""{"""),format.raw/*63.47*/("""
        """),format.raw/*64.9*/("""var c=$("#appnav");
        $.each(data.applications, function(i, obj) """),format.raw/*65.52*/("""{"""),format.raw/*65.53*/("""
          """),format.raw/*66.11*/("""c.append(createElement(1, obj.name, obj.context));
        """),format.raw/*67.9*/("""}"""),format.raw/*67.10*/(""");
      """),format.raw/*68.7*/("""}"""),format.raw/*68.8*/(""", 'json');

      $.get( "/_root/menu", function( data ) """),format.raw/*70.46*/("""{"""),format.raw/*70.47*/("""
        """),format.raw/*71.9*/("""var c=$("#menunav");
        console.log("the data", data)
        $.each(data, function(i, obj) """),format.raw/*73.39*/("""{"""),format.raw/*73.40*/("""
          """),format.raw/*74.11*/("""c.append(createElement(1, obj.segmentId, obj.url));
        """),format.raw/*75.9*/("""}"""),format.raw/*75.10*/(""");
      """),format.raw/*76.7*/("""}"""),format.raw/*76.8*/(""", 'json');
    </script>


    <!--<custom-element></custom-element>-->


    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>"""))
      }
    }
  }

  def render(response:ResponseEventBase,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(response)(content)

  def f:((ResponseEventBase) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (response) => (content) => apply(response)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue Apr 03 09:03:54 CEST 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/main.scala.html
                  HASH: db1b2de4c7a2daff83980e581dc4ddc401ca7b4f
                  MATRIX: 466->1|605->45|632->46|726->115|752->121|779->122|847->164|876->172|909->178|2513->1755|2541->1762|2582->1775|2705->1870|2734->1871|2770->1880|2816->1898|2845->1899|2884->1910|2927->1925|2956->1926|2997->1939|3073->1987|3102->1988|3139->1998|3168->1999|3203->2007|3231->2008|3266->2016|3333->2055|3362->2056|3398->2065|3497->2136|3526->2137|3565->2148|3651->2207|3680->2208|3716->2217|3744->2218|3829->2275|3858->2276|3894->2285|4019->2382|4048->2383|4087->2394|4174->2454|4203->2455|4239->2464|4267->2465
                  LINES: 12->1|17->1|18->2|22->6|22->6|23->7|27->11|27->11|29->13|63->47|63->47|64->48|70->54|70->54|71->55|71->55|71->55|72->56|72->56|72->56|73->57|75->59|75->59|76->60|76->60|77->61|77->61|79->63|79->63|79->63|80->64|81->65|81->65|82->66|83->67|83->67|84->68|84->68|86->70|86->70|87->71|89->73|89->73|90->74|91->75|91->75|92->76|92->76
                  -- GENERATED --
              */
          