# ------------------------------------------------------------------------------------------------------------
# Ozkan SARI - 12501011 - Bilg. Muh. Yuksek Lisans Ogrencisi
# Ileri Programlama Dilleri Dersi Donem Sonu Odevi
#
# PROGRAM ARGUMANLARI:
#    ARGV[0] aramanin yapilacagi hedef URL adresi (Or. http://www.ozkansari.com/index)
#    ARGV[1] aranan metin (Or. java)
#    ARGV[2] bulunan kelimeler gosterilsin mi? (Or. e)
#
# Ornek program cagrimi
#    % ./ozkansari.rb http://www.ozkansari.com/index java h
#    ==> 30
#
# ODEV KONUSU = Verilen bir internet adresine baglanip html dokumaninda istenilen kelimenin bulunmasi
#    Kullanicinin girecegi URL’de bulunan sayfayi alip, yine kullanicinin girecegi bir karakter dizinin bu
#    sayfada  kac  defa  gectigini  bulan  programdir.  URL  gecerli  degilse  hata  uretmeli,  aranan metin
#    bulunmuyorsa 0 uretmelidir.
# ------------------------------------------------------------------------------------------------------------

require 'uri' # http://www.ruby-doc.org/stdlib-2.1.2/libdoc/uri/rdoc/URI.html
require 'open-uri' # http://ruby-doc.org/stdlib-2.1.2/libdoc/open-uri/rdoc/OpenURI.html

# ------------------------------------------------------------------------------------------------------------
# Mevcut web sitesine ait kaynak kod satirinda aranan metnin bulunup bulunmadigini kontrol eder
#
#  @param kaynakKodSatiri : tek bir satirlik karakter katari
#  @param arananMetin : aranan metin
#  @param bulunanlariGoster : bulunan kelimeler gosterilsin mi?
# 
class HtmlKelimeBulucu

  # ------------------------------------------------------------------------------------------------------------
  # Hedef url kaynak kodu icinde aranan metni sorgular
  #
  #  @param hedefUrl : aramanin yapilacagi hedef URL adresi
  #  @param arananMetin : aranan metin
  #  @param bulunanlariGoster : bulunan kelimeler gosterilsin mi?
  # 
  #  @return URL hatalı veya hata olusursa -1; hic sonuc bulunamazsa 0; sonuc bulunursa kac defa bulundugu donulur.
  #
  public
  def ara( hedefUrl, arananMetin, bulunanlariGoster=false)
  
    sayi = 0;
  
    # url gecerliligini kontrol et
    begin
      uri = URI.parse(hedefUrl); # http://www.ruby-doc.org/stdlib-2.1.2/libdoc/uri/rdoc/URI.html
    rescue => e
      case e  
      when URI::InvalidURIError # URL kontrolu
        puts "Hedef URL Gecerli Degil!!! : #{e}";
        return -1;
      else # diger bilinmeyen hatalar
        puts "Bilinmeyen bir hata olustu: #{e}";
        return -1;
      end
    end
  
    begin
      # verilen url acilir ve tek tek satirlarda aranan metin sorgulanir
      uri.open { |f|
        
        f.each_line { | kaynakKodSatiri |

          begin
             bulunan = kaynakKodSatiri.scan(arananMetin).length;
          rescue => e
             # Bazi turkce karakter problemlerini cozmek icin
             begin
               bulunan = kaynakKodSatiri.encode('Windows-1254').scan(arananMetin).length;
             rescue => e
               # Hata cozulmezse bi daha deneme  
               next;
             end
          end
          
          if(bulunanlariGoster && bulunan>0)
            puts ">> " + kaynakKodSatiri;
          end
          sayi += bulunan;
        }
      }
    rescue => e
      puts "Bilinmeyen bir hata olustu: #{e}";
      return -1;
    end
  
    return sayi;
  
  end
  # ------------------------------------------------------------------------------------------------------------
  
end # end-of-class

begin
  if( ARGV.size == 3 ) # Consoledan girdileri al
    hedefUrl = ARGV[0].to_s
    arananMetin = ARGV[1].to_s;
    bulunanlariGoster = !!(ARGV[2] =~ /^(true|t|yes|y|e|1)$/i); # http://stackoverflow.com/a/8120108/878710
  elsif( ARGV.size != 0 )
    raise "Hatali girdi";
  else # Kullanıcıdan girdileri al
    puts "Hedef URL?: "
    hedefUrl = gets.chomp
    puts "Aranan Metin?: "
    arananMetin = gets.chomp  
    puts "Bulunanları Göster?: "
    bulunanlariGoster = gets.chomp
  end
rescue => e
  puts "Bilinmeyen bir hata olustu: #{e}";
  puts "Girdiler hatali olabilir. Ornek program cagirimi './ozkansari.rb ARGV[0] ARGV[1] ARGV[2]' seklinde olmalı.";
  puts ">>> ARGV[0] aramanin yapilacagi hedef URL adresi (Or. http://www.ozkansari.com/index)";
  puts ">>> ARGV[1] aranan metin (Or. java)";
  puts ">>> ARGV[2] bulunan kelimeler gosterilsin mi? 'e' veya 'h' (Or. e)";
  return -1;
end

puts HtmlKelimeBulucu.new().ara( hedefUrl, arananMetin, bulunanlariGoster);

