//package net.andreinc.mockneat.alphabets;
//
///**
// * Copyright 2017, Andrei N. Ciobanu
//
// Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
// documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
// rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
// persons to whom the Software is furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
// Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
// WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
// COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
// OTHERWISE, ARISING FROM, OUT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
// */
//
//
//public enum Colors {
//
//    // TODO class is not yet used
//    // Maybe will never be
//
//    //https://en.wikipedia.org/wiki/List_of_colors:_A–F
//
//    ABSOLUTE_ZERO("Absolute Zero", "#0048BA"),
//    ACID_GREEN("Acid Green", "#B0BF1A"),
//    AERO("Aero", "#7CB9E8"),
//    AERO_BLUE("Aero Blue", "#C9FFE5"),
//    AFRICAN_VIOLET("African Violet", "#B284BE"),
//    AIR_FORCE_BLUE_RAF("Air Force Blue (RAF)", "#5D8AA8"),
//    AIR_FORCE_BLUE_USAF("Air Force Blue (USAF)", "#00308F"),
//    AIR_SUPERIORITY_BLUE("Air Superiority Blue", "#72A0C1"),
//    ALABAMA_CRIMSON("Alabama Crimson", "#AF002A"),
//    ALABASTER("Alabaster", "#F2F0E6"),
//    ALICE_BLUE("Alice Blue", "#F0F8FF"),
//    ALIEN_ARMPIT("Alien Armpit", "#84DE02"),
//    ALIZARIN_CRIMSON("Alizarin Crimson", "#E32636"),
//    ALLOY_ORANGE("Alloy Orange", "#C46210"),
//    ALMOND("Almond", "#EFDECD"),
//    AMARANTH("Amaranth", "#E52B50"),
//    AMARANTH_DEEP_PURPLE("Amaranth Deep Purple", "#9F2B68"),
//    AMARANTH_PINK("Amaranth Pink", "#F19CBB"),
//    AMARANTH_PURPLE("Amaranth Purple", "#AB274F"),
//    AMARANTH_RED("Amaranth Red", "#D3212D"),
//    AMAZON("Amazon", "#3B7A57"),
//    AMAZONITE("Amazonite", "#00C4B0"),
//    AMBER("Amber", "#FFBF00"),
//    AMERICAN_BLUE("American Blue",	"#3B3B6D"),
//    AMERICAN_BROWN("American Brown", "#804040"),
//    AMERICAN_GOLD("American Gold", "#D3AF37"),
//    AMERICAN_GREEN("American Green",	"#34B334"),
//    AMERICAN_ORANGE("American Orange",	"#FF8B00"),
//    AMERICAN_PINK("American Pink",	"#FF9899"),
//    AMERICAN_PURPLE("American Purple",	"#431C53"),
//    AMERICAN_RED("American Red", "#B32134"),
//    AMERICAN_ROSE("American Rose",	"#FF033E"),
//    AMERICAN_SILVER("American Silver",	"#CFCFCF"),
//    AMERICAN_VIOLET("American Violet",	"#551B8C"),
//    AMERICAN_YELLOW("American Yellow",	"#F2B400"),
//    AMETHYST("Amethyst",	"#9966CC"),
//    ANDROID_GREEN("Android Green",	"#A4C639"),
//    ANTI_FLASH_WHITE("Anti-Flash White",	"#F2F3F4"),
//    ANTIQUE_BRASS("Antique Brass",	"#CD9575"),
//    ANTIQUE_BRONZE("Antique Bronze",	"#665D1E"),
//    ANTIQUE_FUCHSIA("Antique Fuchsia",	"#915C83"),
//    ANTIQUE_RUBY("Antique Ruby", "#841B2D"),
//    ANTIQUE_WHITE("Antique White", "#FAEBD7"),
//    AO("Ao", "#008000"),
//    APPLE("Apple",	"#66B447"),
//    APPLE_GREEN("Apple Green",	"#8DB600"),
//    "Apricot	#FBCEB1
//    "Aqua	#00FFFF
//    "Aquamarine	#7FFFD4	50%	100%	83%	160°	100%	75%	50%	100%
//    "Arctic Lime	#D0FF14	82%	100%	8%	72°	100%	54%	92%	100%
//    "Army Green	#4B5320	29%	33%	13%	69°	44%	23%	61%	33%
//    Arsenic	#3B444B	23%	27%	29%	206°	12%	26%	21%	29%
//    Artichoke	#8F9779	56%	59%	47%	76°	13%	53%	20%	59%
//    Arylide Yellow	#E9D66B	91%	84%	42%	51°	74%	67%	54%	91%
//    Ash Gray	#B2BEB5	70%	75%	71%	135°	8%	72%	6%	75%
//    Asparagus	#87A96B	53%	66%	42%	93°	26%	54%	37%	66%
//    Ateneo Blue	#003A6C	0%	23%	42%	208°	100%	21%	100%	42%
//    Atomic Tangerine	#FF9966	100%	60%	40%	20°	100%	70%	60%	100%
//    Auburn	#A52A2A	65%	16%	16%	0°	59%	41%	75%	65%
//    Aureolin	#FDEE00	99%	93%	0%	56°	100%	50%	100%	99%
//    AuroMetalSaurus	#6E7F80	43%	50%	50%	183°	8%	47%	14%	50%
//    Avocado	#568203	34%	51%	1%	81°	95%	26%	98%	51%
//    Awesome	#FF2052	100%	13%	32%	347°	95%	56%	98%	51%
//    Axolotl	#63775B	39%	47%	36%	103°	95%	41%	24%	47%
//    Aztec Gold	#C39953	76%	60%	33%	38°	48%	55%	57%	77%
//    Azure	#007FFF	0%	50%	100%	210°	100%	50%	100%	100%
//    Azure (Web Color)	#F0FFFF	94%	100%	100%	180°	100%	97%	6%	100%
//    Azure Mist	#F0FFFF	94%	100%	100%	180°	100%	97%	6%	100%
//    Azureish White	#DBE9F4	86%	91%	96%	206°	53%	91%	10%	96%
//    Baby Blue	#89CFF0	54%	81%	94%	199°	77%	74%	43%	94%
//    Baby Blue Eyes	#A1CAF1	63%	79%	95%	209°	74%	79%	33%	95%
//    Baby Pink	#F4C2C2	96%	76%	76%	0°	69%	86%	20%	96%
//    Baby Powder	#FEFEFA	100%	100%	98%	60°	67%	99%	2%	100%
//    Baker-Miller Pink	#FF91AF	100%	57%	69%	344°	100%	78%	43%	100%
//    Ball Blue	#21ABCD	13%	67%	80%	192°	72%	47%	84%	80%
//    Banana Mania	#FAE7B5	98%	91%	71%	43°	87%	85%	28%	98%
//    Banana Yellow	#FFE135	100%	88%	21%	51°	100%	60%	79%	100%
//    Bangladesh Green	#006A4E	0%	42%	31%	164°	100%	21%	100%	42%
//    Barbie Pink	#E0218A	88%	13%	54%	327°	75%	50%	85%	88%
//    Barn Red	#7C0A02	49%	4%	1%	4°	97%	25%	98%	49%
//    Battery Charged Blue	#1DACD6	11%	67%	84%	194°	76%	48%	86%	84%
//    Battleship Grey	#848482	52%	52%	51%	60°	1%	51%	2%	52%
//    Bazaar	#98777B	60%	47%	48%	353°	14%	53%	22%	60%
//    Beau Blue	#BCD4E6	74%	83%	90%	206°	46%	82%	18%	90%
//    Beaver	#9F8170	62%	51%	44%	22°	20%	53%	30%	62%
//    Begonia	#FA6E79	98%	43%	47%	355°	93%	71%	10%	96%
//    Beige	#F5F5DC	96%	96%	86%	60°	56%	91%	10%	96%
//    B'dazzled Blue	#2E5894	18%	35%	58%	215°	53%	38%	69%	58%
//    Big Dip O’ruby	#9C2542	61%	15%	26%	345°	62%	38%	76%	61%
//    Big Foot Feet	#E88E5A	91%	56%	35%	22°	76%	63%	61%	91%
//    Bisque	#FFE4C4	100%	89%	77%	33°	100%	88%	23%	100%
//    Bistre	#3D2B1F	24%	17%	12%	24°	33%	18%	49%	24%
//    Bistre Brown	#967117	59%	44%	9%	43°	73%	34%	85%	59%
//    Bitter Lemon	#CAE00D	79%	88%	5%	66°	89%	46%	94%	88%
//    Bitter Lime	#BFFF00	75%	100%	0%	75°	100%	50%	100%	100%
//    Bittersweet	#FE6F5E	100%	44%	37%	6°	99%	68%	63%	100%
//    Bittersweet Shimmer	#BF4F51	75%	31%	32%	359°	47%	53%	59%	75%
//    Black	#000000	0%	0%	0%	0°	0%	0%	0%	0%
//    Black Bean	#3D0C02	24%	5%	1%	10°	94%	12%	97%	24%
//    Black Chocolate	#1B1811	11%	9%	7%	42°	23%	9%	37%	11%
//    Black Coffee	#3B2F2F	23%	18%	18%	0°	11%	21%	20%	23%
//    Black Coral	#54626F	33%	38%	44%	209°	14%	38%	24%	44%
//    Black Leather Jacket	#253529	15%	21%	16%	135°	18%	18%	30%	21%
//    Black Olive	#3B3C36	23%	24%	21%	70°	5%	22%	10%	24%
//    Blackberry	#8F5973	56%	35%	45%	331°	23%	46%	38%	56%
//    Black Shadows	#BFAFB2	75%	69%	70%	349°	11%	72%	8%	75%
//    Blanched Almond	#FFEBCD	100%	92%	80%	36°	100%	90%	20%	100%
//    Blast-Off Bronze	#A57164	65%	44%	39%	12°	27%	52%	39%	65%
//    Bleu De France	#318CE7	19%	55%	91%	210°	79%	55%	79%	91%
//    Blizzard Blue	#ACE5EE	67%	90%	93%	188°	66%	80%	28%	93%
//    Blond	#FAF0BE	98%	94%	75%	50°	86%	86%	24%	98%
//    Blood Orange	#D1001C	82%	0%	11%	352°	100%	41%	100%	82%
//    Blood Red	#660000	40%	0%	0%	0°	100%	20%	100%	40%
//    Blue	#0000FF	0%	0%	100%	240°	100%	50%	100%	100%
//    Blue (Crayola)	#1F75FE	12%	46%	100%	217°	99%	56%	88%	100%
//    Blue (Munsell)	#0093AF	0%	58%	69%	190°	100%	34%	100%	69%
//    Blue (NCS)	#0087BD	0%	53%	74%	197°	100%	37%	100%	74%
//    Blue (Pantone)	#0018A8	0%	9%	66%	231°	100%	33%	100%	66%
//    Blue (Pigment)	#333399	20%	20%	60%	240°	50%	40%	67%	60%
//    Blue (RYB)	#0247FE	1%	28%	100%	224°	99%	50%	99%	100%
//    Blue Bell	#A2A2D0	64%	64%	82%	240°	33%	73%	22%	82%
//    Blue Bolt	#00B9FB	0%	73%	98%	196°	100%	49%	100%	98%
//    Blue-Gray	#6699CC	40%	60%	80%	210°	50%	60%	50%	80%
//    Blue-Green	#0D98BA	5%	60%	73%	192°	87%	39%	93%	73%
//    Blue Jeans	#5DADEC	36%	68%	93%	206°	79%	65%	61%	93%
//    Blue Lagoon	#ACE5EE	67%	90%	93%	188°	66%	80%	28%	93%
//    Blue-Magenta Violet	#553592	33%	21%	57%	261°	47%	39%	64%	57%
//    Blue Sapphire	#126180	7%	38%	50%	197°	75%	29%	86%	50%
//    Blue-Violet	#8A2BE2	54%	17%	89%	271°	76%	53%	81%	89%
//    Blue Yonder	#5072A7	31%	45%	65%	217°	35%	48%	52%	65%
//    Blueberry	#4F86F7	31%	53%	97%	220°	91%	64%	68%	97%
//    Bluebonnet	#1C1CF0	11%	11%	94%	240°	88%	53%	88%	94%
//    Blush	#DE5D83	87%	36%	51%	342°	66%	62%	58%	87%
//    Bole	#79443B	47%	27%	23%	9°	34%	35%	51%	47%
//    Bondi Blue	#0095B6	0%	58%	71%	191°	100%	36%	100%	71%
//    Bone	#E3DAC9	89%	85%	79%	39°	32%	84%	11%	89%
//    Booger Buster	#DDE26A	87%	89%	42%	63°	67%	65%	53%	89%
//    Boston University Red	#CC0000	80%	0%	0%	0°	100%	40%	100%	80%
//    Bottle Green	#006A4E	0%	42%	31%	164°	100%	21%	100%	42%
//    Boysenberry	#873260	53%	20%	38%	328°	46%	36%	63%	53%
//    Brandeis Blue	#0070FF	0%	44%	100%	214°	100%	50%	100%	100%
//    Brass	#B5A642	71%	65%	26%	52°	47%	48%	64%	71%
//    Brick Red	#CB4154	80%	25%	33%	352°	57%	53%	68%	80%
//    Bright Cerulean	#1DACD6	11%	67%	84%	194°	76%	48%	86%	84%
//    Bright Gray	#EBECF0	92%	93%	94%	228°	14%	93%	2%	94%
//    Bright Green	#66FF00	40%	100%	0%	96°	100%	50%	100%	100%
//    Bright Lavender	#BF94E4	75%	58%	89%	272°	60%	74%	35%	89%
//    Bright Lilac	#D891EF	85%	57%	94%	285°	75%	75%	39%	94%
//    Bright Maroon	#C32148	76%	13%	28%	346°	71%	45%	83%	76%
//    Bright Navy Blue	#1974D2	10%	45%	82%	210°	79%	46%	88%	82%
//    Bright Pink	#FF007F	100%	0%	50%	330°	100%	50%	100%	100%
//    Bright Turquoise	#08E8DE	3%	91%	87%	177°	93%	47%	97%	91%
//    Bright Ube	#D19FE8	82%	62%	91%	281°	61%	77%	31%	91%
//    Bright Yellow (Crayola)	#FFAA1D	100%	67%	11%	37°	100%	56%	89%	100%
//    Brilliant Azure	#3399FF	20%	60%	100%	210°	100%	60%	80%	100%
//    Brilliant Lavender	#F4BBFF	96%	73%	100%	290°	100%	87%	27%	100%
//    Brilliant Rose	#FF55A3	100%	33%	64%	332°	100%	67%	67%	100%
//    Brink Pink	#FB607F	98%	38%	50%	348°	95%	68%	62%	98%
//    British Racing Green	#004225	0%	26%	15%	154°	100%	13%	100%	26%
//    Bronze	#88540B	53%	33%	4%	35°	85%	29%	92%	53%
//    Bronze	#CD7F32	80%	50%	20%	30°	61%	50%	76%	80%
//    Bronze (Metallic)	#B08D57	69%	55%	34%	36°	36%	51%	51%	69%
//    Bronze Yellow	#737000	45%	44%	0%	58°	100%	23%	100%	45%
//    Brown	#993300	60%	20%	0%	20°	100%	30%	100%	60%
//    Brown (Crayola)	#AF593E	69%	35%	24%	14°	48%	47%	65%	69%
//    Brown (Traditional)	#964B00	59%	29%	0%	30°	100%	29%	100%	59%
//    Brown (Web)	#A52A2A	65%	16%	16%	0°	59%	41%	75%	65%
//    Brown-Nose	#6B4423	42%	27%	14%	28°	51%	28%	67%	42%
//    Brown Sugar	#AF6E4D	69%	43%	30%	20°	39%	49%	56%	69%
//    Brown Chocolate	#5F1933	37%	10%	20%	338°	58%	24%	74%	37%
//    Brown Coffee	#4A2C2A	29%	17%	16%	4°	28%	23%	43%	29%
//    Brown Yellow	#CC9966	80%	60%	40%	30°	50%	60%	50%	80%
//    Brunswick Green	#1B4D3E	11%	30%	24%	162°	48%	20%	65%	30%
//    Bubble Gum	#FFC1CC	100%	76%	80%	349°	100%	88%	24%	100%
//    Bubbles	#E7FEFF	91%	100%	100%	183°	100%	95%	9%	100%
//    Bud Green	#7BB661	48%	71%	38%	102°	37%	55%	47%	71%
//    Buff	#F0DC82	94%	86%	51%	49°	79%	73%	46%	94%
//    Bulgarian Rose	#480607	28%	2%	3%	359°	85%	15%	92%	28%
//    Burgundy	#800020	50%	0%	13%	345°	100%	25%	100%	50%
//    Burlywood	#DEB887	87%	72%	53%	34°	57%	70%	39%	87%
//    Burnished Brown	#A17A74	63%	48%	45%	8°	19%	54%	28%	63%
//    Burnt Orange	#CC5500	80%	33%	0%	25°	100%	40%	100%	80%
//    Burnt Sienna	#E97451	91%	45%	32%	14°	78%	62%	65%	91%
//    Burnt Umber	#8A3324	54%	20%	14%	9°	59%	34%	74%	54%
//    Button Blue	#24A0ED	14%	63%	93%	203°	85%	54%	83%	93%
//    Byzantine	#BD33A4	74%	20%	64%	311°	58%	47%	73%	74%
//    Byzantium	#702963	44%	16%	39%	311°	46%	30%	63%	44%
//    Cadet	#536872	33%	41%	45%	199°	16%	39%	27%	45%
//    Cadet Blue	#5F9EA0	37%	62%	63%	182°	25%	50%	41%	63%
//    Cadet Grey	#91A3B0	57%	64%	69%	205°	16%	63%	18%	69%
//    Cadmium Blue	#0A1195	4%	7%	57%	237°	87%	31%	93%	85%
//    Cadmium Green	#006B3C	0%	42%	24%	154°	100%	21%	100%	42%
//    Cadmium Orange	#ED872D	93%	53%	18%	28°	84%	55%	81%	93%
//    Cadmium Purple	#B60C26	71%	5%	15%	351°	88%	38%	93%	71%
//    Cadmium Red	#E30022	89%	0%	13%	351°	100%	45%	100%	89%
//    Cadmium Yellow	#FFF600	100%	96%	0%	58°	100%	50%	100%	100%
//    Cadmium Violet	#7F3E98	50%	24%	60%	283°	42%	42%	59%	60%
//    Café Au Lait	#A67B5B	65%	48%	36%	26°	30%	50%	45%	65%
//    Café Noir	#4B3621	29%	21%	13%	30°	39%	21%	56%	29%
//    Cal Poly Pomona Green	#1E4D2B	12%	30%	17%	137°	44%	21%	61%	30%
//    Calamansi	#FCFFA4	99%	100%	64%	62°	100%	82%	36%	100%
//    Cambridge Blue	#A3C1AD	64%	76%	68%	140°	19%	70%	16%	76%
//    Camel	#C19A6B	76%	60%	42%	33°	41%	59%	45%	76%
//    Cameo Pink	#EFBBCC	94%	73%	80%	340°	62%	84%	22%	94%
//    Camouflage Green	#78866B	47%	53%	42%	91°	11%	47%	20%	53%
//    Canary	#FFFF99	100%	100%	60%	60°	100%	80%	40%	100%
//    Canary Yellow	#FFEF00	100%	94%	0%	56°	100%	50%	100%	100%
//    Candy Apple Red	#FF0800	100%	3%	0%	2°	100%	50%	100%	100%
//    Candy Pink	#E4717A	89%	44%	48%	355°	68%	67%	50%	89%
//    Capri	#00BFFF	0%	75%	100%	195°	100%	50%	100%	100%
//    Caput Mortuum	#592720	35%	15%	13%	7°	47%	24%	64%	35%
//    Caramel	#FFD59A	100%	84%	60%	35°	100%	80%	40%	100%
//    Cardinal	#C41E3A	77%	12%	23%	350°	74%	44%	85%	77%
//    Caribbean Green	#00CC99	0%	80%	60%	165°	100%	40%	100%	80%
//    Carmine	#960018	59%	0%	9%	350°	100%	29%	100%	59%
//    Carmine (M&P)	#D70040	84%	0%	25%	342°	100%	42%	100%	84%
//    Carmine Pink	#EB4C42	92%	30%	26%	4°	81%	59%	72%	92%
//    Carmine Red	#FF0038	100%	0%	22%	347°	100%	50%	100%	100%
//    Carnation Pink	#FFA6C9	100%	65%	79%	336°	100%	83%	35%	100%
//    Carnelian	#B31B1B	70%	11%	11%	0°	74%	40%	85%	70%
//    Carolina Blue	#56A0D3	34%	63%	83%	204°	59%	58%	59%	83%
//    Carrot Orange	#ED9121	93%	57%	13%	33°	85%	53%	86%	93%
//    Castleton Green	#00563F	0%	34%	25%	164°	100%	17%	100%	34%
//    Catalina Blue	#062A78	2%	16%	47%	221°	90%	25%	95%	47%
//    Catawba	#703642	44%	21%	26%	348°	35%	33%	52%	44%
//    Cedar Chest	#C95A49	79%	35%	29%	8°	54%	54%	64%	79%
//    Ceil	#92A1CF	57%	63%	81%	225°	39%	69%	29%	81%
//    Celadon	#ACE1AF	67%	88%	69%	123°	47%	78%	24%	88%
//    Celadon Blue	#007BA7	0%	48%	65%	196°	100%	33%	100%	65%
//    Celadon Green	#2F847C	18%	52%	49%	174°	47%	35%	64%	52%
//    Celeste	#B2FFFF	70%	100%	100%	180°	100%	85%	30%	100%
//    Celestial Blue	#4997D0	29%	59%	82%	205°	59%	55%	65%	82%
//    Cerise	#DE3163	87%	19%	39%	343°	72%	53%	78%	87%
//    Cerise Pink	#EC3B83	93%	23%	51%	336°	82%	58%	75%	93%
//    Cerulean	#007BA7	0%	48%	65%	196°	100%	33%	100%	65%
//    Cerulean Blue	#2A52BE	16%	32%	75%	224°	64%	45%	78%	75%
//    Cerulean Frost	#6D9BC3	43%	61%	76%	208°	42%	60%	44%	76%
//    CG Blue	#007AA5	0%	48%	65%	196°	100%	32%	100%	65%
//    CG Red	#E03C31	88%	24%	19%	4°	74%	54%	78%	88%
//    Chamoisee	#A0785A	63%	47%	35%	26°	28%	49%	44%	63%
//    Champagne	#F7E7CE	97%	91%	81%	37°	72%	89%	17%	97%
//    Champagne Pink	#F1DDCF	95%	87%	81%	25°	55%	88%	14%	95%
//    Charcoal	#36454F	21%	27%	31%	204°	19%	26%	32%	31%
//    Charleston Green	#232B2B	14%	17%	17%	180°	10%	15%	19%	17%
//    Charm	#D0748B	82%	45%	55%	345°	50%	64%	44%	82%
//    Charm Pink	#E68FAC	90%	56%	67%	340°	64%	73%	38%	90%
//    Chartreuse (Traditional)	#DFFF00	87%	100%	0%	68°	100%	50%	100%	100%
//    Chartreuse (Web)	#7FFF00	50%	100%	0%	90°	100%	50%	100%	100%
//    Cheese	#FFA600	100%	65%	0%	39°	100%	50%	100%	100%
//    Cherry	#DE3163	87%	19%	39%	343°	72%	53%	78%	87%
//    Cherry Blossom Pink	#FFB7C5	100%	72%	77%	348°	100%	86%	28%	100%
//    Chestnut	#954535	58%	27%	21%	10°	48%	40%	64%	58%
//    China Pink	#DE6FA1	87%	44%	63%	333°	63%	65%	50%	87%
//    China Rose	#A8516E	66%	32%	43%	340°	35%	49%	52%	66%
//    Chinese Black	#141414	8%	8%	8%	0°	0%	8%	0%	8%
//    Chinese Blue	#365194	21%	32%	58%	223°	47%	40%	64%	58%
//    Chinese Bronze	#CD8032	80%	50%	20%	30°	61%	50%	76%	80%
//    Chinese Brown	#AB381F	67%	22%	12%	11°	70%	39%	82%	67%
//    Chinese Green	#D0DB61	82%	86%	38%	65°	63%	62%	56%	86%
//    Chinese Gold	#CC9900	80%	60%	0%	45°	100%	40%	100%	80%
//    Chinese Orange	#F37042	95%	44%	26%	16°	88%	61%	73%	95%
//    Chinese Pink	#DE70A1	87%	44%	63%	333°	63%	66%	50%	87%
//    Chinese Purple	#720B98	45%	4%	60%	284°	87%	32%	93%	60%
//    Chinese Red	#CD071E	80%	3%	12%	353°	93%	42%	97%	80%
//    Chinese Red	#AA381E	67%	22%	12%	11°	70%	39%	82%	67%
//    Chinese Silver	#CCCCCC	80%	80%	80%	0°	0%	80%	0%	80%
//    Chinese Violet	#856088	52%	38%	53%	296°	17%	45%	29%	53%
//    Chinese White	#E2E5DE	89%	90%	87%	86°	12%	88%	3%	90%
//    Chinese Yellow	#FFB200	100%	70%	0%	42°	100%	50%	100%	100%
//    Chlorophyll Green	#4AFF00	29%	100%	0%	103°	100%	50%	100%	100%
//    Chocolate Kisses	#3C1421	24%	8%	13%	341°	50%	16%	67%	33%
//    Chocolate (Traditional)	#7B3F00	48%	25%	0%	31°	100%	24%	100%	48%
//    Chocolate (Web)	#D2691E	82%	41%	12%	25°	75%	47%	86%	82%
//    Christmas Blue	#2A8FBD	16%	56%	74%	199°	64%	45%	78%	74%
//    Christmas Blue	#365194	21%	32%	58%	223°	47%	40%	64%	58%
//    Christmas Brown	#5D2B2C	36%	17%	17%	359°	37%	27%	54%	36%
//    Christmas Brown	#4C1F02	30%	12%	1%	24°	95%	15%	24%	30%
//    Christmas Green	#3C8D0D	24%	55%	5%	98°	83%	30%	91%	55%
//    Christmas Green	#007502	0%	46%	1%	158°	100%	23%	100%	46%
//    Christmas Gold	#CAA906	79%	66%	2%	50°	94%	41%	97%	79%
//    Christmas Orange	#FF6600	100%	40%	0%	24°	100%	50%	100%	100%
//    Christmas Orange	#D56C2B	84%	42%	17%	23°	67%	50%	80%	84%
//    Christmas Pink	#FFCCCB	100%	80%	80%	1°	100%	90%	20%	100%
//    Christmas Pink	#E34285	89%	26%	52%	335°	74%	58%	71%	89%
//    Christmas Purple	#663398	40%	20%	60%	270°	50%	40%	66%	60%
//    Christmas Purple	#4D084B	30%	3%	30%	302°	81%	17%	90%	30%
//    Christmas Red	#AA0114	67%	0%	8%	353°	99%	34%	99%	67%
//    Christmas Red	#B01B2E	69%	11%	18%	352°	73%	40%	85%	69%
//    Christmas Silver	#E1DFE0	88%	87%	88%	330°	3%	88%	1%	88%
//    Christmas Yellow	#FFCC00	100%	80%	0%	48°	100%	50%	100%	100%
//    Christmas Yellow	#FEF200	100%	95%	0%	57°	100%	50%	100%	100%
//    Chrome Yellow	#FFA700	100%	65%	0%	39°	100%	50%	100%	100%
//    Cinereous	#98817B	60%	51%	48%	12°	12%	54%	19%	60%
//    Cinnabar	#E34234	89%	26%	20%	5°	76%	55%	77%	89%
//    Cinnamon[Citation Needed]	#D2691E	82%	41%	12%	25°	75%	47%	86%	82%
//    Cinnamon Satin	#CD607E	80%	38%	49%	343°	52%	59%	53%	80%
//    Citrine	#E4D00A	89%	82%	4%	54°	92%	47%	96%	89%
//    Citrine Brown	#933709	58%	22%	4%	20°	89%	31%	94%	58%
//    Citron	#9FA91F	62%	66%	12%	64°	69%	39%	82%	66%
//    Claret	#7F1734	50%	9%	20%	343°	69%	29%	82%	50%
//    Classic Rose	#FBCCE7	98%	80%	91%	326°	85%	89%	19%	98%
//    Cobalt Blue	#0047AB	0%	28%	67%	215°	100%	34%	100%	67%
//    Cocoa Brown	#D2691E	82%	41%	12%	25°	75%	47%	86%	82%
//    Coconut	#965A3E	59%	35%	24%	19°	42%	42%	59%	59%
//    Coffee	#6F4E37	44%	31%	22%	25°	34%	33%	50%	44%
//    Cola	#3C3024	24%	19%	14%	30°	25%	19%	40%	24%
//    Columbia Blue	#C4D8E2	77%	85%	89%	200°	34%	83%	13%	89%
//    Conditioner	#FFFFCC	100%	100%	80%	60°	100%	90%	20%	100%
//    Congo Pink	#F88379	97%	51%	47%	5°	90%	72%	51%	97%
//    Cool Black	#002E63	0%	18%	39%	212°	100%	19%	100%	38%
//    Cool Grey	#8C92AC	55%	57%	67%	229°	16%	61%	19%	67%
//    Cookies And Cream	#EEE0B1	93%	88%	69%	46°	64%	81%	26%	93%
//    Copper	#B87333	72%	45%	20%	29°	57%	46%	72%	72%
//    Copper (Crayola)	#DA8A67	85%	54%	40%	18°	61%	63%	53%	85%
//    Copper Penny	#AD6F69	68%	44%	41%	5°	29%	55%	39%	68%
//    Copper Red	#CB6D51	80%	43%	32%	14°	54%	56%	60%	80%
//    Copper Rose	#996666	60%	40%	40%	0°	20%	50%	33%	60%
//    Coquelicot	#FF3800	100%	22%	0%	13°	100%	50%	100%	100%
//    Coral	#FF7F50	100%	50%	31%	16°	100%	66%	69%	100%
//    Coral Pink	#F88379	97%	51%	47%	5°	90%	72%	51%	97%
//    Coral Red	#FF4040	100%	25%	25%	0°	100%	63%	75%	100%
//    Coral Reef	#FD7C6E	99%	49%	43%	6°	97%	71%	57%	99%
//    Cordovan	#893F45	54%	25%	27%	355°	37%	39%	54%	54%
//    Corn	#FBEC5D	98%	93%	36%	54°	95%	67%	63%	98%
//    Cornell Red	#B31B1B	70%	11%	11%	0°	74%	40%	85%	70%
//    Cornflower Blue	#6495ED	39%	58%	93%	219°	79%	66%	58%	93%
//    Cornsilk	#FFF8DC	100%	97%	86%	48°	100%	93%	14%	100%
//    Cosmic Cobalt	#2E2D88	18%	18%	53%	241°	50%	36%	67%	53%
//    Cosmic Latte	#FFF8E7	100%	97%	91%	43°	100%	95%	9%	100%
//    Coyote Brown	#81613C	51%	38%	24%	62°	37%	37%	52%	51%
//    Cotton Candy	#FFBCD9	100%	74%	85%	334°	100%	87%	26%	100%
//    Cream	#FFFDD0	100%	99%	82%	57°	100%	91%	18%	100%
//    Crimson	#DC143C	86%	8%	24%	348°	83%	47%	91%	86%
//    Crimson Glory	#BE0032	75%	0%	20%	344°	100%	37%	100%	75%
//    Crimson Red	#990000	60%	0%	0%	0°	100%	30%	100%	60%
//    Cultured	#F5F5F5	96%	96%	96%	0°	0%	96%	0%	96%
//    Cyan	#00FFFF	0%	100%	100%	180°	100%	50%	100%	100%
//    Cyan Azure	#4E82B4	31%	51%	71%	209°	41%	51%	57%	71%
//    Cyan-Blue Azure	#4682BF	27%	51%	75%	210°	49%	51%	63%	75%
//    Cyan Cobalt Blue	#28589C	16%	35%	61%	215°	59%	38%	74%	61%
//    Cyan Cornflower Blue	#188BC2	9%	55%	76%	199°	78%	43%	88%	76%
//    Cyan (Process)	#00B7EB	0%	72%	92%	193°	100%	46%	100%	92%
//    Cyber Grape	#58427C	35%	26%	49%	263°	31%	37%	47%	49%
//    Cyber Yellow	#FFD300	100%	83%	0%	50°	100%	50%	100%	100%
//    Cyclamen	#F56FA1	96%	44%	63%	338°	87%	70%	54%	96%
//    Daffodil	#FFFF31	100%	100%	19%	60°	100%	60%	81%	100%
//    Dandelion	#F0E130	94%	88%	19%	55°	86%	56%	80%	94%
//    Dark Blue	#00008B	0%	0%	55%	240°	100%	27%	100%	55%
//    Dark Blue-Gray	#666699	40%	40%	60%	240°	20%	50%	33%	60%
//    Dark Bronze	#804A00	50%	29%	0%	100°	100%	25%	76%	50%
//    Dark Brown	#654321	40%	26%	13%	30°	51%	26%	67%	40%
//    Dark Brown-Tangelo	#88654E	53%	40%	31%	24°	27%	42%	43%	53%
//    Dark Byzantium	#5D3954	36%	22%	33%	315°	24%	29%	39%	36%
//    Dark Candy Apple Red	#A40000	64%	0%	0%	0°	100%	32%	100%	64%
//    Dark Cerulean	#08457E	3%	27%	49%	209°	88%	26%	94%	49%
//    Dark Charcoal	#333333	20%	20%	20%	0°	0%	20%	0%	20%
//    Dark Chestnut	#986960	60%	41%	38%	10°	23%	49%	37%	60%
//    Dark Chocolate	#490206	29%	1%	2%	358°	95%	15%	97%	29%
//    Dark Chocolate (Hershey's)	#3C1321	24%	7%	13%	340°	52%	16%	68%	24%
//            Dark Cornflower Blue	#26428B	15%	26%	55%	223°	57%	35%	73%	55%
//            Dark Coral	#CD5B45	80%	36%	27%	10°	58%	54%	66%	80%
//            Dark Cyan	#008B8B	0%	55%	55%	180°	100%	27%	100%	55%
//            Dark Electric Blue	#536878	33%	41%	47%	206°	18%	40%	31%	47%
//            Dark Goldenrod	#B8860B	72%	53%	4%	43°	89%	38%	94%	72%
//            Dark Gray (X11)	#A9A9A9	66%	66%	66%	—°	0%	66%	0%	66%
//    Dark Green	#013220	0%	20%	13%	158°	96%	10%	98%	20%
//    Dark Green (X11)	#006400	0%	39%	0%	120°	100%	20%	100%	39%
//    Dark Gunmetal	#1F262A	12%	15%	16%	202°	15%	14%	26%	16%
//    Dark Imperial Blue	#00416A	0%	25%	42%	203°	100%	21%	100%	42%
//    Dark Imperial Blue	#00147E	0%	8%	49%	231°	100%	25%	100%	40%
//    Dark Jungle Green	#1A2421	10%	14%	13%	162°	16%	12%	28%	14%
//    Dark Khaki	#BDB76B	74%	72%	42%	56°	38%	58%	43%	74%
//    Dark Lava	#483C32	28%	24%	20%	27°	18%	24%	31%	28%
//    Dark Lavender	#734F96	45%	31%	59%	270°	31%	45%	47%	59%
//    Dark Lemon Lime	#8BBE1B	55%	75%	11%	79°	75%	43%	86%	75%
//    Dark Liver	#534B4F	33%	29%	31%	330°	5%	31%	10%	33%
//    Dark Liver (Horses)	#543D37	33%	24%	22%	12°	21%	27%	35%	33%
//    Dark Magenta	#8B008B	55%	0%	55%	300°	100%	27%	100%	55%
//    Dark Medium Gray	#A9A9A9	66%	66%	66%	—°	0%	66%	0%	66%
//    Dark Midnight Blue	#003366	0%	20%	40%	210°	100%	20%	100%	40%
//    Dark Moss Green	#4A5D23	29%	36%	14%	80°	45%	25%	62%	36%
//    Dark Olive Green	#556B2F	33%	42%	18%	82°	39%	30%	56%	42%
//    Dark Orange	#FF8C00	100%	55%	0%	33°	100%	50%	100%	100%
//    Dark Orchid	#9932CC	60%	20%	80%	280°	61%	50%	75%	80%
//    Dark Pastel Blue	#779ECB	47%	62%	80%	212°	45%	63%	41%	80%
//    Dark Pastel Green	#03C03C	1%	75%	24%	138°	97%	38%	98%	75%
//    Dark Pastel Purple	#966FD6	59%	44%	84%	263°	56%	64%	48%	84%
//    Dark Pastel Red	#C23B22	76%	23%	13%	9°	70%	45%	82%	76%
//    Dark Pink	#E75480	91%	33%	50%	342°	75%	62%	64%	91%
//    Dark Powder Blue	#003399	0%	20%	60%	220°	100%	30%	100%	60%
//    Dark Puce	#4F3A3C	31%	23%	24%	354°	15%	27%	27%	31%
//    Dark Purple	#301934	19%	10%	20%	291°	35%	15%	51%	20%
//    Dark Raspberry	#872657	53%	15%	34%	330°	56%	34%	72%	53%
//    Dark Red	#8B0000	55%	0%	0%	0°	100%	27%	100%	55%
//    Dark Salmon	#E9967A	91%	59%	48%	15°	72%	70%	48%	91%
//    Dark Scarlet	#560319	34%	1%	10%	344°	93%	17%	97%	34%
//    Dark Sea Green	#8FBC8F	56%	74%	56%	120°	25%	65%	24%	74%
//    Dark Sienna	#3C1414	24%	8%	8%	0°	50%	16%	67%	24%
//    Dark Sky Blue	#8CBED6	55%	75%	84%	199°	47%	69%	35%	84%
//    Dark Slate Blue	#483D8B	28%	24%	55%	248°	39%	39%	56%	55%
//    Dark Slate Gray	#2F4F4F	18%	31%	31%	180°	25%	25%	41%	31%
//    Dark Spring Green	#177245	9%	45%	27%	150°	66%	27%	80%	45%
//    Dark Tan	#918151	57%	51%	32%	45°	28%	44%	44%	57%
//    Dark Tangerine	#FFA812	100%	66%	7%	38°	100%	54%	93%	100%
//    Dark Taupe	#483C32	28%	24%	20%	27°	18%	24%	31%	28%
//    Dark Terra Cotta	#CC4E5C	80%	31%	36%	353°	55%	55%	62%	80%
//    Dark Turquoise	#00CED1	0%	81%	82%	181°	100%	41%	100%	82%
//    Dark Vanilla	#D1BEA8	82%	75%	66%	32°	31%	74%	20%	82%
//    Dark Violet	#9400D3	58%	0%	83%	282°	100%	41%	100%	83%
//    Dark Yellow	#9B870C	61%	53%	5%	52°	86%	33%	92%	61%
//    Dartmouth Green	#00703C	0%	44%	24%	152°	100%	22%	100%	44%
//    Davy's Grey	#555555	33%	33%	33%	—°	0%	33%	0%	33%
//    Debian Red	#D70A53	84%	4%	33%	339°	91%	44%	95%	84%
//    Deep Amethyst	#9C8AA4	61%	54%	64%	282°	13%	59%	16%	16%
//    Deep Aquamarine	#40826D	25%	51%	43%	161°	34%	38%	51%	51%
//    Deep Carmine	#A9203E	66%	13%	24%	347°	68%	39%	81%	66%
//    Deep Carmine Pink	#EF3038	94%	19%	22%	357°	86%	56%	80%	94%
//    Deep Carrot Orange	#E9692C	91%	41%	17%	19°	81%	54%	81%	91%
//    Deep Cerise	#DA3287	85%	20%	53%	330°	69%	53%	77%	85%
//    Deep Champagne	#FAD6A5	98%	84%	65%	35°	90%	81%	34%	98%
//    Deep Chestnut	#B94E48	73%	31%	28%	3°	45%	50%	61%	73%
//    Deep Coffee	#704241	44%	26%	25%	1°	27%	35%	42%	44%
//    Deep Fuchsia	#C154C1	76%	33%	76%	300°	47%	54%	56%	76%
//    Deep Green	#056608	2%	40%	3%	122°	91%	21%	95%	40%
//    Deep Green-Cyan Turquoise	#0E7C61	5%	49%	38%	165°	80%	27%	89%	49%
//    Deep Jungle Green	#004B49	0%	29%	29%	178°	100%	15%	100%	29%
//    Deep Koamaru	#333366	20%	20%	40%	240°	33%	30%	50%	40%
//    Deep Lemon	#F5C71A	96%	78%	10%	47°	92%	53%	89%	96%
//    Deep Lilac	#9955BB	60%	33%	73%	280°	43%	53%	55%	73%
//    Deep Magenta	#CC00CC	80%	0%	80%	300°	100%	40%	100%	80%
//    Deep Maroon	#820000	51%	0%	0%	0°	100%	26%	100%	51%
//    Deep Mauve	#D473D4	83%	45%	83%	300°	53%	64%	46%	83%
//    Deep Moss Green	#355E3B	21%	37%	23%	129°	28%	29%	44%	37%
//    Deep Peach	#FFCBA4	100%	80%	64%	26°	100%	82%	36%	100%
//    Deep Pink	#FF1493	100%	8%	58%	328°	100%	54%	92%	100%
//    Deep Puce	#A95C68	66%	36%	41%	351°	31%	51%	46%	66%
//    Deep Red	#850101	52%	0%	0%	0°	99%	26%	99%	52%
//    Deep Ruby	#843F5B	52%	25%	36%	336°	35%	38%	52%	52%
//    Deep Saffron	#FF9933	100%	60%	20%	30°	100%	60%	80%	100%
//    Deep Sky Blue	#00BFFF	0%	75%	100%	195°	100%	50%	100%	100%
//    Deep Space Sparkle	#4A646C	29%	39%	42%	194°	19%	36%	31%	42%
//    Deep Spring Bud	#556B2F	33%	42%	18%	82°	39%	30%	56%	42%
//    Deep Taupe	#7E5E60	49%	37%	38%	356°	15%	43%	25%	49%
//    Deep Tuscan Red	#66424D	40%	26%	30%	342°	21%	33%	35%	40%
//    Deep Violet	#330066	20%	0%	40%	270°	100%	20%	100%	40%
//    Deer	#BA8759	73%	53%	35%	28°	41%	54%	52%	73%
//    Denim	#1560BD	8%	38%	74%	213°	80%	41%	89%	74%
//    Denim Blue	#2243B6	13%	26%	71%	227°	69%	42%	81%	71%
//    Desaturated Cyan	#669999	40%	60%	60%	180°	20%	50%	33%	60%
//    Desert	#C19A6B	76%	60%	42%	33°	41%	59%	45%	76%
//    Desert Sand	#EDC9AF	93%	79%	69%	25°	63%	81%	26%	93%
//    Desire	#EA3C53	92%	24%	33%	352°	81%	58%	74%	92%
//    Diamond	#B9F2FF	73%	95%	100%	191°	100%	86%	27%	100%
//    Dim Gray	#696969	41%	41%	41%	—°	0%	41%	0%	41%
//    Dingy Dungeon	#C53151	77%	19%	32%	347°	60%	48%	75%	77%
//    Dirt	#9B7653	61%	46%	33%	29°	30%	47%	46%	61%
//    Dirty Brown	#B5651E	71%	40%	12%	28°	72%	41%	83%	71%
//    Dirty White	#E8E4C9	91%	89%	79%	52°	40%	85%	13%	91%
//    Dodger Blue	#1E90FF	12%	56%	100%	210°	100%	56%	88%	100%
//    Dodie Yellow	#FEF65B	100%	96%	36%	57°	99%	68%	64%	100%
//    Dogwood Rose	#D71868	84%	9%	41%	335°	80%	47%	89%	84%
//    Dollar Bill	#85BB65	52%	73%	40%	98°	39%	56%	46%	73%
//    Dolphin Gray	#828E84	51%	56%	52%	130°	5%	53%	8%	56%
//    Donkey Brown	#664C28	40%	30%	16%	35°	44%	28%	61%	40%
//    Drab	#967117	59%	44%	9%	43°	73%	34%	85%	59%
//    Duke Blue	#00009C	0%	0%	61%	240°	100%	31%	100%	61%
//    Dust Storm	#E5CCC9	90%	80%	79%	6°	35%	84%	12%	90%
//    Dutch White	#EFDFBB	94%	87%	73%	42°	62%	84%	22%	94%
//    Earth Yellow	#E1A95F	88%	66%	37%	34°	68%	63%	58%	88%
//    Ebony	#555D50	33%	36%	31%	97°	8%	34%	14%	36%
//    Ecru	#C2B280	76%	70%	50%	45°	35%	63%	34%	76%
//    Eerie Black	#1B1B1B	11%	11%	11%	—°	0%	11%	0%	11%
//    Eggplant	#614051	38%	25%	32%	329°	20%	32%	34%	38%
//    Eggshell	#F0EAD6	94%	92%	84%	46°	46%	89%	11%	94%
//    Egyptian Blue	#1034A6	6%	20%	65%	226°	82%	36%	90%	65%
//    Electric Blue	#7DF9FF	49%	98%	100%	183°	100%	75%	51%	100%
//    Electric Crimson	#FF003F	100%	0%	25%	345°	100%	50%	100%	100%
//    Electric Cyan	#00FFFF	0%	100%	100%	180°	100%	50%	100%	100%
//    Electric Green	#00FF00	0%	100%	0%	120°	100%	50%	100%	100%
//    Electric Indigo	#6F00FF	44%	0%	100%	266°	100%	50%	100%	100%
//    Electric Lavender	#F4BBFF	96%	73%	100%	290°	100%	87%	27%	100%
//    Electric Lime	#CCFF00	80%	100%	0%	72°	100%	50%	100%	100%
//    Electric Purple	#BF00FF	75%	0%	100%	285°	100%	50%	100%	100%
//    Electric Ultramarine	#3F00FF	25%	0%	100%	255°	100%	50%	100%	100%
//    Electric Violet	#8F00FF	56%	0%	100%	274°	100%	50%	100%	100%
//    Electric Yellow	#FFFF33	100%	100%	20%	60°	100%	60%	80%	100%
//    Emerald	#50C878	31%	78%	47%	140°	52%	55%	60%	78%
//    Emerald Green	#046307	2%	39%	3%	122°	92%	20%	96%	39%
//    Eminence	#6C3082	42%	19%	51%	284°	46%	35%	63%	51%
//    English Green	#1B4D3E	11%	30%	24%	162°	48%	20%	65%	30%
//    English Lavender	#B48395	71%	51%	58%	338°	25%	61%	27%	71%
//    English Red	#AB4B52	67%	29%	32%	356°	39%	48%	56%	67%
//    English Vermillion	#CC474B	80%	28%	29%	358°	57%	54%	65%	80%
//    English Violet	#563C5C	34%	24%	36%	289°	21%	30%	35%	36%
//    Eton Blue	#96C8A2	59%	78%	64%	134°	31%	69%	25%	78%
//    Eucalyptus	#44D7A8	27%	84%	66%	161°	65%	55%	68%	84%
//    Fallow	#C19A6B	76%	60%	42%	33°	41%	59%	45%	76%
//    Falu Red	#801818	50%	9%	9%	0°	68%	30%	81%	50%
//    Fandango	#B53389	71%	20%	54%	320°	56%	45%	72%	71%
//    Fandango Pink	#DE5285	87%	32%	52%	338°	68%	60%	63%	87%
//    Fashion Fuchsia	#F400A1	96%	0%	63%	320°	100%	48%	100%	96%
//    Fawn	#E5AA70	90%	67%	44%	30°	69%	67%	51%	90%
//    Feldgrau	#4D5D53	30%	36%	33%	143°	9%	33%	17%	36%
//    Feldspar	#FDD5B1	99%	84%	69%	28°	95%	84%	30%	99%
//    Fern Green	#4F7942	31%	47%	26%	106°	29%	37%	45%	47%
//    Ferrari Red	#FF2800	100%	16%	0%	9°	100%	50%	100%	100%
//    Field Drab	#6C541E	42%	33%	12%	42°	57%	27%	72%	42%
//    Fiery Rose	#FF5470	100%	33%	44%	350°	100%	67%	67%	100%
//    Firebrick	#B22222	70%	13%	13%	0°	68%	42%	81%	70%
//    Fire Engine Red	#CE2029	81%	13%	16%	357°	73%	47%	84%	81%
//    Fire Opal	#E95C4B	91%	36%	29%	6°	78%	60%	68%	91%
//    Flame	#E25822	89%	35%	13%	17°	77%	51%	85%	89%
//    Flamingo Pink	#FC8EAC	99%	56%	67%	344°	95%	77%	44%	99%
//    Flattery	#6B4423	42%	27%	14%	28°	51%	28%	67%	42%
//    Flavescent	#F7E98E	97%	91%	56%	52°	87%	76%	43%	97%
//    Flax	#EEDC82	93%	86%	51%	50°	76%	72%	45%	93%
//    Flesh	#FFE9D1	100%	91%	82%	31°	100%	91%	18%	100%
//    Flirt	#A2006D	64%	0%	43%	320°	100%	32%	100%	64%
//    Floral White	#FFFAF0	100%	98%	94%	40°	100%	97%	6%	100%
//    Fluorescent Orange	#FFBF00	100%	75%	0%	45°	100%	50%	100%	100%
//    Fluorescent Pink	#FF1493	100%	8%	58%	328°	100%	54%	92%	100%
//    Fluorescent Yellow	#CCFF00	80%	100%	0%	72°	100%	50%	100%	100%
//    Folly	#FF004F	100%	0%	31%	341°	100%	50%	100%	100%
//    Forest Green (Traditional)	#014421	0%	27%	13%	149°	97%	14%	99%	27%
//    Forest Green (Web)	#228B22	13%	55%	13%	120°	61%	34%	76%	55%
//    French Beige	#A67B5B	65%	48%	36%	26°	30%	50%	45%	65%
//    French Bistre	#856D4D	52%	43%	30%	34°	27%	41%	42%	52%
//    French Blue	#0072BB	0%	45%	73%	203°	100%	37%	100%	73%
//    French Fuchsia	#FD3F92	99%	25%	57%	334°	98%	62%	75%	99%
//    French Lilac	#86608E	53%	38%	56%	290°	19%	47%	32%	56%
//    French Lime	#9EFD38	62%	99%	22%	89°	98%	61%	78%	99%
//    French Mauve	#D473D4	83%	45%	83%	300°	53%	64%	46%	83%
//    French Pink	#FD6C9E	99%	42%	62%	339°	97%	71%	57%	99%
//    French Plum	#811453	51%	8%	33%	325°	73%	29%	84%	51%
//    French Puce	#4E1609	31%	9%	4%	11°	79%	17%	88%	31%
//    French Raspberry	#C72C48	78%	17%	28%	349°	64%	48%	78%	78%
//    French Rose	#F64A8A	96%	29%	54%	338°	91%	63%	70%	96%
//    French Sky Blue	#77B5FE	47%	71%	100%	212°	99%	73%	53%	100%
//    French Violet	#8806CE	53%	2%	81%	279°	94%	42%	97%	81%
//    French Wine	#AC1E44	67%	12%	27%	344°	70%	40%	83%	67%
//    Fresh Air	#A6E7FF	65%	91%	100%	196°	100%	83%	35%	100%
//    Frostbite	#E936A7	91%	21%	65%	322°	80%	56%	77%	91%
//    Fuchsia	#FF00FF	100%	0%	100%	300°	100%	50%	100%	100%
//    Fuchsia (Crayola)	#C154C1	76%	33%	76%	300°	47%	54%	56%	76%
//    Fuchsia Pink	#FF77FF	100%	47%	100%	300°	100%	73%	53%	100%
//    Fuchsia Purple	#CC397B	80%	22%	48%	333°	59%	51%	72%	80%
//    Fuchsia Rose	#C74375	78%	26%	46%	337°	54%	52%	66%	78%
//    Fulvous	#E48400	89%	52%	0%	35°	100%	45%	100%	89%
//    Fuzzy Wuzzy	#CC6666	80%	40%	40%	0°	50%	60%	50%	80%
//
//    ;
//
//    //TODO complete the rest
//
//    private final String name;
//    private final String hex;
//
//    Colors(String name, String hex) {
//        this.name = name;
//        this.hex = hex;
//    }
//
//    public String longName() {
//        return name;
//    }
//
//    public String hex() {
//        return hex;
//    }
//}
