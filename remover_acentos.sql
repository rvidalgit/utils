create function remover_acentos(character varying) returns character varying
  immutable
  language plpgsql
as
$$
declare
        buffer text;
    begin
        buffer := TRANSLATE($1, 'µ¸ÀÁÂÃÄÅÇÈÉÊËÌÍÎÏÑÒÓÔÕÖ×ÙÚÛÜÝàáâãäåçèéêëìíîïñòóôõöùúûüý', 'u,AAAAAACEEEEIIIINOOOOOxUUUUYaaaaaaceeeeiiiinooooouuuuy');
        buffer := TRANSLATE(buffer, 'àáảãạăắằẳẵặâấầẩẫậäāąåαάἀἁἂἃἄἅἆἇᾀᾁᾂᾃᾄᾅᾆᾇὰάᾰᾱᾲᾳᾴᾶᾷаأȃǟǡǻȁȧ', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa');
        buffer := TRANSLATE(buffer, 'бβЪЬبƀƂƃƄƅ', 'bbbbbbbbbb');
        buffer := TRANSLATE(buffer, 'çćčĉċ¢ȼƈ', 'cccccccc');
        buffer := TRANSLATE(buffer, 'ďðđƌȡɖɗᵭᶁᶑдδدض', 'dddddddddddddd');
        buffer := TRANSLATE(buffer, 'éèẻẽẹêếềểễệëēęěĕėεέἐἑἒἓἔἕὲέеёэєə℮ƍƏǝȇȅȩ∧', 'eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee');
        buffer := TRANSLATE(buffer, 'фφف', 'fff');
        buffer := TRANSLATE(buffer, 'ĝğġģгґγجǵǥǧ', 'ggggggggggg');
        buffer := TRANSLATE(buffer, 'ĥħηήحهƕǶ', 'hhhhhhhh');
        buffer := TRANSLATE(buffer, 'íìỉĩịîïīĭįıιίϊΐἰἱἲἳἴἵἶἷὶίῐῑῒΐῖῗіїиǐȋȉⅠⅰ', 'iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii');
        buffer := TRANSLATE(buffer, 'ĵјЈ', 'jjj');
        buffer := TRANSLATE(buffer, 'ķĸкκĶقكǩ', 'kkkkkkkk');
        buffer := TRANSLATE(buffer, 'łľĺļŀлλلƚ', 'lllllllll');
        buffer := TRANSLATE(buffer, 'мμمṁʍⅿ', 'mmmmmm');
        buffer := TRANSLATE(buffer, 'ñńňņŉŋνнنƞǹⁿ', 'nnnnnnnnnnnn');
        buffer := TRANSLATE(buffer, 'óòỏõọôốồổỗộơớờởỡợøōőŏοὀὁὂὃὄὅὸόöоوθȱȫȭȯ', 'oooooooooooooooooooooooooooooooooooooo');
        buffer := TRANSLATE(buffer, 'пπṗƣ', 'pppp');
        buffer := TRANSLATE(buffer, 'ŕřŗрρرяȓȑ', 'rrrrrrrrr');
        buffer := TRANSLATE(buffer, 'śšşсσșςسصṡƨ', 'sssssssssss');
        buffer := TRANSLATE(buffer, 'ťţтτțتطƫƭȶ', 'tttttttttt');
        buffer := TRANSLATE(buffer, 'úùủũụưứừửữựüûūůűŭųµуȗȕ', 'uuuuuuuuuuuuuuuuuuuuuu');
        buffer := TRANSLATE(buffer, 'вⅤⅴ', 'vvv');
        buffer := TRANSLATE(buffer, 'ŵωώẁẃẅⱳʬʷ', 'wwwwwwwww');
        buffer := TRANSLATE(buffer, 'χ×жхⅩⅹх', 'xxxxxxx');
        buffer := TRANSLATE(buffer, 'ýỳỷỹỵÿŷйыυϋύΰيȳ', 'yyyyyyyyyyyyyyy');
        buffer := TRANSLATE(buffer, 'źžżзζزᵶƶȥ', 'zzzzzzzzz');
        buffer := replace(buffer, 'ع', 'aa');
        buffer := replace(buffer, 'æ', 'ae');
        buffer := replace(buffer, 'ч', 'ch');
        buffer := replace(buffer, 'ђ', 'dj');
        buffer := replace(buffer, 'đ', 'dj');
        buffer := replace(buffer, 'џ', 'dz');
        buffer := replace(buffer, 'ǆ', 'dz');
        buffer := replace(buffer, 'ǳ', 'dz');
        buffer := replace(buffer, 'غ', 'gh');
        buffer := replace(buffer, 'خ', 'kh');
        buffer := replace(buffer, 'љ', 'lj');
        buffer := replace(buffer, 'љ', 'lj');
        buffer := replace(buffer, 'ǉ', 'lj');
        buffer := replace(buffer, 'њ', 'nj');
        buffer := replace(buffer, 'œ', 'oe');
        buffer := replace(buffer, 'ψ', 'ps');
        buffer := replace(buffer, '㎰', 'ps');
        buffer := replace(buffer, 'ш', 'sh');
        buffer := replace(buffer, 'щ', 'shch');
        buffer := replace(buffer, 'ß', 'ss');
        buffer := replace(buffer, 'þ', 'th');
        buffer := replace(buffer, 'ث', 'th');
        buffer := replace(buffer, 'ظ', 'th');
        buffer := replace(buffer, 'ظ', 'th');
        buffer := replace(buffer, 'ᵺ', 'th');
        buffer := replace(buffer, 'ц', 'ts');
        buffer := replace(buffer, 'я', 'ya');
        buffer := replace(buffer, 'ю', 'yu');
        buffer := replace(buffer, 'ж', 'zh');
        buffer := replace(buffer, '©', '(c)');
        buffer := TRANSLATE(buffer, 'ÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬÄÅĀĄΑΆἈἉἊἋἌἍἎἏᾈᾉᾊᾋᾌᾍᾎᾏᾸᾹᾺΆᾼАǞǠǺȀȂȦȺ', 'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA');
        buffer := TRANSLATE(buffer, 'БΒƁɃ', 'BBBB');
        buffer := TRANSLATE(buffer, 'ÇĆČĈĊȻƆƇȻ', 'CCCCCCCCC');
        buffer := TRANSLATE(buffer, 'ĎÐĐƉƊƋᴅᴆДΔḊȡ', 'DDDDDDDDDDDD');
        buffer := TRANSLATE(buffer, 'ÉÈẺẼẸÊẾỀỂỄỆËĒĘĚĔĖΕΈἘἙἚἛἜἝΈῈЕЁЭЄƏ€ƎƐȄȆȨ∃∈∊∋', 'EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE');
        buffer := TRANSLATE(buffer, 'ФΦ', 'FF');
        buffer := TRANSLATE(buffer, 'ĞĠĢГҐΓǤǦǴ', 'GGGGGGGGG');
        buffer := TRANSLATE(buffer, 'ΗΉῌ', 'HHH');
        buffer := TRANSLATE(buffer, 'ÍÌỈĨỊÎÏĪĬĮİΙΊΪἸἹἻἼἽἾἿῘῙῚΊИІЇǏȈȊ', 'IIIIIIIIIIIIIIIIIIIIIIIIIIIIIII');
        buffer := TRANSLATE(buffer, 'КΚƘƙǨ', 'KKKKK');
        buffer := TRANSLATE(buffer, 'ĹŁЛΛĻ£ĿƛȴȽ', 'LLLLLLLLLL');
        buffer := TRANSLATE(buffer, 'МΜṀƜ', 'MMMM');
        buffer := TRANSLATE(buffer, 'ŃÑŇŅŊНΝ∏', 'NNNNNNNN');
        buffer := TRANSLATE(buffer, 'ÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÖØŌŐŎΟΌὈὉὊὋὌὍῸΌОΘӨȌȎȪȬȮȰ', 'OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO');
        buffer := TRANSLATE(buffer, 'ПΠṖ℗ƢƤƥ', 'PPPPPPP');
        buffer := TRANSLATE(buffer, 'ŘŔРΡ®ᴙᴚƦȐȒ', 'RRRRRRRRRR');
        buffer := TRANSLATE(buffer, 'ŞŜȘŠŚСΣṠſƧ∑', 'SSSSSSSSSSS');
        buffer := TRANSLATE(buffer, 'ŤŢŦȚТΤṪƬƮȾ', 'TTTTTTTTTT');
        buffer := TRANSLATE(buffer, 'ÚÙỦŨỤƯỨỪỬỮỰÛÜŪŮŰŬŲУȔȖɄ', 'UUUUUUUUUUUUUUUUUUUUUU');
        buffer := TRANSLATE(buffer, 'ВƲ', 'VV');
        buffer := TRANSLATE(buffer, 'ΩΏẀẂẄⱲ', 'WWWWWW');
        buffer := TRANSLATE(buffer, 'ΧЖХ', 'XXX');
        buffer := TRANSLATE(buffer, 'ÝỲỶỸỴŸῨῩῪΎЫЙΥΫ¥ƳƴȲ', 'YYYYYYYYYYYYYYYYYY');
        buffer := TRANSLATE(buffer, 'ŹŽŻЗΖƵȤ', 'ZZZZZZZ');
        buffer := replace(buffer, 'Æ', 'AE');
        buffer := replace(buffer, 'Ч', 'CH');
        buffer := replace(buffer, 'Ђ', 'DJ');
        buffer := replace(buffer, 'Џ', 'DZ');
        buffer := replace(buffer, 'Ǆ', 'DZ');
        buffer := replace(buffer, 'ǅ', 'DZ');
        buffer := replace(buffer, 'Ǳ', 'DZ');
        buffer := replace(buffer, 'ǲ', 'DZ');
        buffer := replace(buffer, 'Х', 'KH');
        buffer := replace(buffer, 'Љ', 'LJ');
        buffer := replace(buffer, 'Њ', 'NJ');
        buffer := replace(buffer, 'Ψ', 'PS');
        buffer := replace(buffer, 'Ш', 'SH');
        buffer := replace(buffer, 'Щ', 'SHCH');
        buffer := replace(buffer, 'ẞ', 'SS');
        buffer := replace(buffer, 'Þ', 'TH');
        buffer := replace(buffer, 'Ц', 'TS');
        buffer := replace(buffer, 'Я', 'YA');
        buffer := replace(buffer, 'Ю', 'YU');
        buffer := replace(buffer, 'Ж', 'ZH');
        buffer := replace(buffer, 'Ⅱ', 'ii');
        buffer := replace(buffer, 'ⅱ', 'ii');
        buffer := replace(buffer, 'Ⅲ', 'iii');
        buffer := replace(buffer, 'ⅲ', 'iii');
        buffer := replace(buffer, 'Ⅳ', 'iv');
        buffer := replace(buffer, 'ⅳ', 'iv');
        buffer := replace(buffer, 'Ⅸ', 'ix');
        buffer := replace(buffer, 'ⅸ', 'ix');
        buffer := TRANSLATE(buffer, 'ǭǫ', 'qq');
        buffer := replace(buffer, 'Ⅵ', 'vi');
        buffer := replace(buffer, 'ⅵ', 'vi');
        buffer := replace(buffer, 'Ⅶ', 'vii');
        buffer := replace(buffer, 'ⅶ', 'vii');
        buffer := replace(buffer, 'Ⅷ', 'viii');
        buffer := replace(buffer, 'ⅷ', 'viii');
        buffer := replace(buffer, 'Ⅺ', 'xi');
        buffer := replace(buffer, 'ⅺ', 'xi');
        buffer := replace(buffer, 'Ⅻ', 'xii');
        buffer := replace(buffer, 'ⅻ', 'xii');
        buffer := TRANSLATE(buffer, 'Ĵǰ', 'JJ');
        buffer := TRANSLATE(buffer, 'ǪǬ', 'QQ');
        buffer := replace(buffer, '℅', 'co');
        buffer := replace(buffer, 'ƹ', 'ezh');
        buffer := replace(buffer, 'ƹ', 'ezh');
        buffer := replace(buffer, 'ƺ', 'ezh');
        buffer := replace(buffer, 'ᵫ', 'ue');
        buffer := replace(buffer, '№', 'No');
        buffer := replace(buffer, '™', 'tm');
        buffer := replace(buffer, '√', 'raiz');
        buffer := replace(buffer, '∨', 'ou');
        buffer := replace(buffer, '℠', 'SM');
        buffer := replace(buffer, 'Ʃ', 'ESH');
        buffer := replace(buffer, 'ƪ', 'ESH');
        buffer := replace(buffer, 'Ʒ', 'EZH');
        buffer := replace(buffer, 'Ƹ', 'EZH');
        buffer := replace(buffer, 'ƿ', 'WYNN');
        buffer := replace(buffer, 'Ƿ', 'WYNN');
        buffer := replace(buffer, '∼', '~');
        buffer := replace(buffer, '≈', '~~');
        buffer := replace(buffer, '≃', '~-');
        buffer := replace(buffer, '≅', '~--');
        buffer := replace(buffer, '¸', ',');
        buffer := TRANSLATE(buffer, '‘’', '''');
        buffer := TRANSLATE(buffer, '―¯‒–—−─­ˍˉ˗', '-----------');
        buffer := TRANSLATE(buffer, '”“', '""');
        buffer := replace(buffer, ';', ';');
        buffer := TRANSLATE(buffer, '¿Ɂɂ', '???');
        buffer := TRANSLATE(buffer, '¡ǃ', '!!');
        buffer := TRANSLATE(buffer, '¦ǀǁ', '|||');
        buffer := TRANSLATE(buffer, '⁽⁾₍₎', '()()');
        buffer := TRANSLATE(buffer, '⁺⁻₊₋', '+-+-');
        buffer := replace(buffer, '∠', '<');
        buffer := replace(buffer, '«', '<<');
        buffer := replace(buffer, '≪', '<<');
        buffer := replace(buffer, '»', '>>');
        buffer := replace(buffer, '≫', '>>');
        buffer := replace(buffer, '≧', '>=');
        buffer := replace(buffer, '≥', '>=');
        buffer := replace(buffer, '≤', '<=');
        buffer := replace(buffer, '≦', '<=');
        buffer := replace(buffer, '±', '+-');
        buffer := replace(buffer, '∓', '-+');
        buffer := TRANSLATE(buffer, '·•∙', '...');
        buffer := replace(buffer, '∞', 'inf');
        buffer := TRANSLATE(buffer, '¹₁１', '111');
        buffer := TRANSLATE(buffer, '²ƻ₂２', '2222');
        buffer := TRANSLATE(buffer, '³ᶾ₃３', '3333');
        buffer := TRANSLATE(buffer, '⁴₄４', '444');
        buffer := TRANSLATE(buffer, '⁵Ƽƽ₅５', '55555');
        buffer := TRANSLATE(buffer, '⁶₆６', '666');
        buffer := TRANSLATE(buffer, '⁷₇７', '777');
        buffer := TRANSLATE(buffer, '⁸₈８', '888');
        buffer := TRANSLATE(buffer, '⁹₉９', '999');
        buffer := TRANSLATE(buffer, '⁰°₀０', '0000');
        buffer := replace(buffer, '¼', '1/4');
        buffer := replace(buffer, '½', '1/2');
        buffer := replace(buffer, '¾', '3/4');
        buffer := replace(buffer, '⅓', '1/3');
        buffer := replace(buffer, '⅔', '2/3');
        buffer := replace(buffer, '⅛', '1/8');
        buffer := replace(buffer, '⅜', '3/8');
        buffer := replace(buffer, '⅝', '5/8');
        buffer := replace(buffer, '⅞', '7/8');
        buffer := replace(buffer, '℡', 'tel');
        buffer := replace(buffer, '℉', '0F');
        buffer := replace(buffer, '㍱', 'hPa');
        buffer := replace(buffer, '㍲', 'da');
        buffer := replace(buffer, '㍳', 'AU');
        buffer := replace(buffer, '℀', 'ac');
        buffer := replace(buffer, '㍴', 'bar');
        buffer := replace(buffer, '㍵', 'oV');
        buffer := replace(buffer, '㍶', 'pc');
        buffer := replace(buffer, '㍷', 'dm');
        buffer := replace(buffer, '㍸', 'dm2');
        buffer := replace(buffer, '㍹', 'dm3');
        buffer := replace(buffer, '㍺', 'IU');
        buffer := replace(buffer, '㎀', 'pA');
        buffer := replace(buffer, '㎁', 'nA');
        buffer := replace(buffer, '㎂', 'uA');
        buffer := replace(buffer, '㎃', 'mA');
        buffer := replace(buffer, '㎄', 'kA');
        buffer := replace(buffer, '㎅', 'KB');
        buffer := replace(buffer, '㎆', 'MB');
        buffer := replace(buffer, '㎇', 'GB');
        buffer := replace(buffer, '㎈', 'cal');
        buffer := replace(buffer, '㎉', 'kcal');
        buffer := replace(buffer, '㎊', 'pF');
        buffer := replace(buffer, '㎋', 'nF');
        buffer := replace(buffer, '㎌', 'uF');
        buffer := replace(buffer, '㎍', 'ug');
        buffer := replace(buffer, '㎎', 'mg');
        buffer := replace(buffer, '㎏', 'kg');
        buffer := replace(buffer, '㎐', 'Hz');
        buffer := replace(buffer, '㎑', 'kHz');
        buffer := replace(buffer, '㎒', 'MHz');
        buffer := replace(buffer, '㎓', 'GHz');
        buffer := replace(buffer, '㎔', 'THz');
        buffer := replace(buffer, '㎕', 'ul');
        buffer := replace(buffer, '㎖', 'ml');
        buffer := replace(buffer, '㎗', 'dl');
        buffer := replace(buffer, '㎘', 'kl');
        buffer := replace(buffer, '㎙', 'fm');
        buffer := replace(buffer, '㎚', 'nm');
        buffer := replace(buffer, '㎛', 'um');
        buffer := replace(buffer, '㎜', 'mm');
        buffer := replace(buffer, '㎝', 'cm');
        buffer := replace(buffer, '㎞', 'km');
        buffer := replace(buffer, '㎟', 'mm2');
        buffer := replace(buffer, '㎠', 'cm2');
        buffer := replace(buffer, '㎡', 'm2');
        buffer := replace(buffer, '㎢', 'km2');
        buffer := replace(buffer, '㎣', 'mm3');
        buffer := replace(buffer, '㎤', 'cm3');
        buffer := replace(buffer, '㎥', 'm3');
        buffer := replace(buffer, '㎦', 'km3');
        buffer := replace(buffer, '㎧', 'm/s');
        buffer := replace(buffer, '㎨', 'm/s2');
        buffer := replace(buffer, '㎩', 'Pa');
        buffer := replace(buffer, '㎪', 'kPa');
        buffer := replace(buffer, '㎫', 'MPa');
        buffer := replace(buffer, '㎬', 'GPa');
        buffer := replace(buffer, '㎭', 'rad');
        buffer := replace(buffer, '㎮', 'rad/s');
        buffer := replace(buffer, '㎯', 'rad/s2');
        buffer := replace(buffer, '㎱', 'ns');
        buffer := replace(buffer, '㎲', 'us');
        buffer := replace(buffer, '㎳', 'ms');
        buffer := replace(buffer, '㎴', 'pV');
        buffer := replace(buffer, '㎵', 'nV');
        buffer := replace(buffer, '㎶', 'uV');
        buffer := replace(buffer, '㎷', 'mV');
        buffer := replace(buffer, '㎸', 'kV');
        buffer := replace(buffer, '㎹', 'MV');
        buffer := replace(buffer, '㎺', 'pW');
        buffer := replace(buffer, '㎻', 'nW');
        buffer := replace(buffer, '㎼', 'uW');
        buffer := replace(buffer, '㎽', 'mW');
        buffer := replace(buffer, '㎾', 'kW');
        buffer := replace(buffer, '㎿', 'MW');
        buffer := replace(buffer, '㏀', 'ko');
        buffer := replace(buffer, '㏁', 'mo');
        buffer := replace(buffer, '㏂', 'a.m.');
        buffer := replace(buffer, '㏃', 'Bq');
        buffer := replace(buffer, '㏄', 'cc');
        buffer := replace(buffer, '㏅', 'cd');
        buffer := replace(buffer, '㏆', 'C/Kg');
        buffer := replace(buffer, '㏇', 'Co.');
        buffer := replace(buffer, '㏈', 'dB');
        buffer := replace(buffer, '㏉', 'Gy');
        buffer := replace(buffer, '㏊', 'ha');
        buffer := replace(buffer, '㏋', 'HP');
        buffer := replace(buffer, '㏌', 'in');
        buffer := replace(buffer, '㏍', 'K.K.');
        buffer := replace(buffer, '㏎', 'KM');
        buffer := replace(buffer, '㏏', 'kt');
        buffer := replace(buffer, '㏐', 'lm');
        buffer := replace(buffer, '㏑', 'ln');
        buffer := replace(buffer, '㏒', 'log');
        buffer := replace(buffer, '㏓', 'lx');
        buffer := replace(buffer, '㏔', 'mb');
        buffer := replace(buffer, '㏕', 'mil');
        buffer := replace(buffer, '㏖', 'mol');
        buffer := replace(buffer, '㏗', 'pH');
        buffer := replace(buffer, '㏘', 'p.m.');
        buffer := replace(buffer, '㏙', 'PPM');
        buffer := replace(buffer, '㏚', 'PR');
        buffer := replace(buffer, '㏛', 'sr');
        buffer := replace(buffer, '㏜', 'Sv');
        buffer := replace(buffer, '㏝', 'Wb');
        buffer := replace(buffer, '㏞', 'v/m');
        buffer := replace(buffer, '㏟', 'a/m');
        buffer := replace(buffer, '㏿', 'gal');
        buffer := TRANSLATE(buffer, '†‡♠♣♥♦¶§�', '');
        return buffer;
    end;
$$;

alter function remover_acentos(varchar) owner to root;

