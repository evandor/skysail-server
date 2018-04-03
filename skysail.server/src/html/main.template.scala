
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
                                <div id="tree"></div>
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
                """),_display_(/*48.18*/content),format.raw/*48.25*/("""
            """),format.raw/*49.13*/("""</main>
        </div>
    </div>

    <script>

      function createElement(i, name,context) """),format.raw/*55.47*/("""{"""),format.raw/*55.48*/("""
        """),format.raw/*56.9*/("""return $('<div>', """),format.raw/*56.27*/("""{"""),format.raw/*56.28*/("""
          """),format.raw/*57.11*/("""html: $('<a>', """),format.raw/*57.26*/("""{"""),format.raw/*57.27*/("""
            """),format.raw/*58.13*/("""href: context,
            text: "["+name+"]"
          """),format.raw/*60.11*/("""}"""),format.raw/*60.12*/(""")
        """),format.raw/*61.9*/("""}"""),format.raw/*61.10*/(""")
      """),format.raw/*62.7*/("""}"""),format.raw/*62.8*/("""

      """),format.raw/*64.7*/("""$.get( "/_root/apps", function( data ) """),format.raw/*64.46*/("""{"""),format.raw/*64.47*/("""
        """),format.raw/*65.9*/("""var c=$("#appnav");
        $.each(data.applications, function(i, obj) """),format.raw/*66.52*/("""{"""),format.raw/*66.53*/("""
          """),format.raw/*67.11*/("""c.append(createElement(1, obj.name, obj.context));
        """),format.raw/*68.9*/("""}"""),format.raw/*68.10*/(""");
      """),format.raw/*69.7*/("""}"""),format.raw/*69.8*/(""", 'json');

      // $.get( "/_root/menu", function( data ) """),format.raw/*71.49*/("""{"""),format.raw/*71.50*/("""
      """),format.raw/*72.7*/("""//   var c=$("#menunav");
      //   console.log("the data", data.items)
      //   $.each(data.items, function(i, obj) """),format.raw/*74.48*/("""{"""),format.raw/*74.49*/("""
      """),format.raw/*75.7*/("""//     c.append(createElement(1, obj.segmentId, obj.url));
      //   """),format.raw/*76.12*/("""}"""),format.raw/*76.13*/(""");
      // """),format.raw/*77.10*/("""}"""),format.raw/*77.11*/(""", 'json');


      var e = $('#tree')
      $.get( "/_root/menu", function( data ) """),format.raw/*81.46*/("""{"""),format.raw/*81.47*/("""
          """),format.raw/*82.11*/("""var json = JSON.parse("[" + JSON.stringify(data) + "]")
          //console.log("3",JSON.stringify(json))


           var t = [
               """),format.raw/*87.16*/("""{"""),format.raw/*87.17*/("""
                   """),format.raw/*88.20*/("""text: 'Parent 1!',
                   href: '#parent1',
                   tags: ['4'],
                   nodes: [
                       """),format.raw/*92.24*/("""{"""),format.raw/*92.25*/("""
                           """),format.raw/*93.28*/("""text: 'Child 1',
                           href: '#child1',
                           tags: ['2'],
                           nodes: [
                               """),format.raw/*97.32*/("""{"""),format.raw/*97.33*/("""
                                   """),format.raw/*98.36*/("""text: 'Grandchild 1',
                                   href: '#grandchild1',
                                   tags: ['0']
                               """),format.raw/*101.32*/("""}"""),format.raw/*101.33*/(""",
                               """),format.raw/*102.32*/("""{"""),format.raw/*102.33*/("""
                                   """),format.raw/*103.36*/("""text: 'Grandchild 2',
                                   href: '#grandchild2',
                                   tags: ['0']
                               """),format.raw/*106.32*/("""}"""),format.raw/*106.33*/("""
                           """),format.raw/*107.28*/("""]
                       """),format.raw/*108.24*/("""}"""),format.raw/*108.25*/(""",
                       """),format.raw/*109.24*/("""{"""),format.raw/*109.25*/("""
                           """),format.raw/*110.28*/("""text: 'Child 2',
                           href: '#child2',
                           tags: ['0']
                       """),format.raw/*113.24*/("""}"""),format.raw/*113.25*/("""
                   """),format.raw/*114.20*/("""]
               """),format.raw/*115.16*/("""}"""),format.raw/*115.17*/(""",
               """),format.raw/*116.16*/("""{"""),format.raw/*116.17*/("""
                   """),format.raw/*117.20*/("""text: 'Parent 2',
                   href: '#parent2',
                   tags: ['0']
               """),format.raw/*120.16*/("""}"""),format.raw/*120.17*/(""",
               """),format.raw/*121.16*/("""{"""),format.raw/*121.17*/("""
                   """),format.raw/*122.20*/("""text: 'Parent 3',
                   href: '#parent3',
                   tags: ['0']
               """),format.raw/*125.16*/("""}"""),format.raw/*125.17*/(""",
               """),format.raw/*126.16*/("""{"""),format.raw/*126.17*/("""
                   """),format.raw/*127.20*/("""text: 'Parent 4',
                   href: '#parent4',
                   tags: ['0']
               """),format.raw/*130.16*/("""}"""),format.raw/*130.17*/(""",
               """),format.raw/*131.16*/("""{"""),format.raw/*131.17*/("""
                   """),format.raw/*132.20*/("""text: 'Parent 5',
                   href: '#parent5'  ,
                   tags: ['0']
               """),format.raw/*135.16*/("""}"""),format.raw/*135.17*/("""
           """),format.raw/*136.12*/("""]

          e.treeview("""),format.raw/*138.22*/("""{"""),format.raw/*138.23*/("""
              """),format.raw/*139.15*/("""color: "#428bca",
              expandIcon: 'glyphicon glyphicon-chevron-right',
              collapseIcon: 'glyphicon glyphicon-chevron-down',
              nodeIcon: 'glyphicon glyphicon-bookmark',
              data: t"""),format.raw/*143.22*/("""}"""),format.raw/*143.23*/(""");
          //e.treeview('expandAll', """),format.raw/*144.37*/("""{"""),format.raw/*144.38*/(""" """),format.raw/*144.39*/("""levels: 2, silent: true """),format.raw/*144.63*/("""}"""),format.raw/*144.64*/(""");
      """),format.raw/*145.7*/("""}"""),format.raw/*145.8*/(""");





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
                  DATE: Tue Apr 03 17:32:29 CEST 2018
                  SOURCE: C:/git/skysail-server/skysail.server/./src/main.scala.html
                  HASH: e8cbce0f6c6011f24825ce125310a78adec552b2
                  MATRIX: 466->1|605->45|632->46|726->115|752->121|779->122|847->164|876->172|909->178|2567->1809|2595->1816|2636->1829|2759->1924|2788->1925|2824->1934|2870->1952|2899->1953|2938->1964|2981->1979|3010->1980|3051->1993|3135->2049|3164->2050|3201->2060|3230->2061|3265->2069|3293->2070|3328->2078|3395->2117|3424->2118|3460->2127|3559->2198|3588->2199|3627->2210|3713->2269|3742->2270|3778->2279|3806->2280|3894->2340|3923->2341|3957->2348|4105->2468|4134->2469|4168->2476|4266->2546|4295->2547|4335->2559|4364->2560|4475->2643|4504->2644|4543->2655|4715->2799|4744->2800|4792->2820|4959->2959|4988->2960|5044->2988|5240->3156|5269->3157|5333->3193|5519->3350|5549->3351|5611->3384|5641->3385|5706->3421|5892->3578|5922->3579|5979->3607|6033->3632|6063->3633|6117->3658|6147->3659|6204->3687|6356->3810|6386->3811|6435->3831|6481->3848|6511->3849|6557->3866|6587->3867|6636->3887|6766->3988|6796->3989|6842->4006|6872->4007|6921->4027|7051->4128|7081->4129|7127->4146|7157->4147|7206->4167|7336->4268|7366->4269|7412->4286|7442->4287|7491->4307|7623->4410|7653->4411|7694->4423|7747->4447|7777->4448|7821->4463|8072->4685|8102->4686|8170->4725|8200->4726|8230->4727|8283->4751|8313->4752|8350->4761|8379->4762
                  LINES: 12->1|17->1|18->2|22->6|22->6|23->7|27->11|27->11|29->13|64->48|64->48|65->49|71->55|71->55|72->56|72->56|72->56|73->57|73->57|73->57|74->58|76->60|76->60|77->61|77->61|78->62|78->62|80->64|80->64|80->64|81->65|82->66|82->66|83->67|84->68|84->68|85->69|85->69|87->71|87->71|88->72|90->74|90->74|91->75|92->76|92->76|93->77|93->77|97->81|97->81|98->82|103->87|103->87|104->88|108->92|108->92|109->93|113->97|113->97|114->98|117->101|117->101|118->102|118->102|119->103|122->106|122->106|123->107|124->108|124->108|125->109|125->109|126->110|129->113|129->113|130->114|131->115|131->115|132->116|132->116|133->117|136->120|136->120|137->121|137->121|138->122|141->125|141->125|142->126|142->126|143->127|146->130|146->130|147->131|147->131|148->132|151->135|151->135|152->136|154->138|154->138|155->139|159->143|159->143|160->144|160->144|160->144|160->144|160->144|161->145|161->145
                  -- GENERATED --
              */
          