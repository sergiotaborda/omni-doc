<?xml version="1.0" encoding="ISO-8859-1" ?>

<!DOCTYPE stylesheet [
<!ENTITY plusmn  "&#177;" ><!-- the +- sign -->
]>

<xsl:stylesheet version="2.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:fn="http://www.w3.org/2005/xpath-functions"
	xmlns:xdt="http://www.w3.org/2005/xpath-datatypes"
	xmlns:err="http://www.w3.org/2005/xqt-errors"
	exclude-result-prefixes="xs xdt err fn">

	<xsl:output method="text"  indent="no" omit-xml-declaration="yes" media-type="text/plain"/>
	<xsl:strip-space elements="*" />
	
	<xsl:template match="*|text()|node()"><xsl:copy><xsl:apply-templates/></xsl:copy></xsl:template>
	
	<xsl:template match="document">
		<xsl:apply-templates select="body"/>
		<!-- <xsl:apply-templates select="references"/>  -->
	</xsl:template>
	
	<xsl:template match="body">[*<xsl:value-of select="//properties/title/text()"/>*]

<xsl:apply-templates/>
	</xsl:template>

	<!--  
	<xsl:template match="references">
		<div class="references" >
		<h3 class="ttl">Referências</h3>
		<table class="referencetable">
		<xsl:for-each select="ref">
			<tr>
				<td align="center" valign="top">
				[<xsl:value-of select="@id"/>]
				</td>
				<td align="left" valign="top">
				<strong>
				<xsl:value-of select="title"/>
				</strong><br/>
				<i>
				<xsl:for-each select="author">
					<xsl:value-of select="text()"/>
					<xsl:if test="position() != last()" >
					    , 
					</xsl:if>
				</xsl:for-each>
				</i><br/>

				<xsl:if test="string-length(book/text()) &gt; 0">
				Livro:<a href="/library/books/{book/text()}.html" title="{title/text()}" ><xsl:value-of select="title/text()"/></a><br/>
				</xsl:if>
				
				<xsl:if test="string-length(editor/text()) &gt; 0">
				Editor:<xsl:apply-templates match="editor"/><br/>
				</xsl:if>
				
				<xsl:if test="string-length(url/text()) &gt; 0">
				URL:<xsl:apply-templates match="url"/><br/>
				</xsl:if>
				
				<p><xsl:value-of select="abstract"/></p>
				</td>
			</tr>
		</xsl:for-each>
		</table>
		</div>
	</xsl:template>
	

	
	<xsl:template match="etable">
		<table colspan="0" align="center" border="1"  >
			 <xsl:apply-templates select="@* | node()"/>
		</table>
	</xsl:template>
	
	-->
	
	<xsl:template match="table">
[table]
<xsl:copy><xsl:apply-templates select="@* | node()"/></xsl:copy>
[/table]
	</xsl:template>
	
	<xsl:template match="tr">
[tr]<xsl:copy><xsl:apply-templates />[/tr]</xsl:copy>
	</xsl:template>
	
	<xsl:template match="th">[th]<xsl:apply-templates/>[/th]</xsl:template>
	<xsl:template match="td">[td]<xsl:apply-templates/>[/td]</xsl:template>
	
	<xsl:template match="quote">[quote=<xsl:value-of select="@author"/>]<xsl:apply-templates/>[/cote]</xsl:template>
	
	<xsl:template match="url">[url={text()}]<xsl:value-of select="text()"/>[/url]</xsl:template>
	
	<xsl:template match="a">[url={@href}]<xsl:value-of select="text()"/>[/url]</xsl:template>
	
	<xsl:template match="body/section">
[h2]<xsl:value-of select="@name"/>[/h2]<xsl:apply-templates/>
	</xsl:template>
	
	<xsl:template match="page/section">
[h2]<xsl:value-of select="@name"/>[h2]<xsl:apply-templates/>
	</xsl:template>
	
	<xsl:template match="page"><xsl:apply-templates/></xsl:template>
	
	<xsl:template match="section/section">
[h3]<xsl:value-of select="@name"/>[/h3]<xsl:apply-templates/>
	</xsl:template>
	
	<xsl:template match="section/section/section">
[h4]<xsl:value-of select="@name"/>[/h4]<xsl:apply-templates/>
	</xsl:template>
	
	<xsl:template match="plusminus">
&plusmn;
	</xsl:template>
	
	
	<xsl:template match="source">
[code=java]
<xsl:value-of select="text()"/>
[/code]
	</xsl:template>
	
	<xsl:template match="code">[icode]<xsl:value-of select="text()"/>[/icode]</xsl:template>
	
	<xsl:template match="figure">
	[img=/ilust/{img/@src}]{legend/text()}[/img]
	</xsl:template>
	
	<xsl:template match="img">
	[img=/ilust/{@src}][/img]
	</xsl:template>
	
	<xsl:template match="emph">[i]<xsl:value-of select="text()"/>[/i]</xsl:template>
	
	<xsl:template match="keyword">[keyword]<xsl:value-of select="text()"/>[/keyword]</xsl:template>
	
	<xsl:template match="class">
		<xsl:choose>
			<xsl:when test="string-length(name)">[class={@name}]<xsl:value-of select="text()"/>[/class]</xsl:when>
			<xsl:otherwise>[class]<xsl:value-of select="text()"/>[/class]</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template match="p">
	
<xsl:apply-templates/>
</xsl:template>

	<xsl:template match="wiki">
		<xsl:choose>
			<xsl:when test="string-length(@lang)">
				<xsl:choose>
					<xsl:when test="string-length(@key)">[wiki={@lang};{@key}]<xsl:apply-templates/>[/wiki]</xsl:when>
					<xsl:otherwise>[wiki={{@lang};text()}]<xsl:apply-templates/>[/wiki]</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:otherwise>
				<xsl:choose>
					<xsl:when test="string-length(@key)">[wiki=pt;{@key}]<xsl:apply-templates/>[/wiki]</xsl:when>
					<xsl:otherwise>[wiki=pt;{text()}]<xsl:apply-templates/>[/wiki]</xsl:otherwise>
				</xsl:choose>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template match="estrang">[e]<xsl:apply-templates/>[/e]</xsl:template>


	<xsl:template match="pattern">
		<xsl:choose>
		  <xsl:when test="string-length(@ref)">[pattern=<xsl:value-of select="@ref"/>]<xsl:value-of select="text()"/>[/pattern]</xsl:when>
		  <xsl:otherwise>[pattern]<xsl:value-of select="text()"/>[/pattern]</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<!-- 
	<xsl:template match="ref">[ref=<xsl:value-of select="@id"/>/]</xsl:template>
	 -->
</xsl:stylesheet>
