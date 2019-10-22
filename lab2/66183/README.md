#Install package

    In folder /src:
    pip3 install -r requirements.txt

#Run scrapper

    python3 main.py with arguments
    
    Arguments:

    For help:
    -h, --help            show this help message and exit

    Essential arguments:
  
    -p                     Podaj czy interesuje cie dom czy mieszkanie
    -rt                    Podaj czy interesuje cie wynajem czy sprzedaz
    -c                     Podaj misto ktore cie interesuje
    -d                     Podaj dzielnice ktore cie interesuje
    
    Example: 
    
    python3 main.py -p mieszkanie -rt wynajem -c warszawa -d bemowo


#Additional info
    Scrapper create dirs /img & /json collaterally to /src dir
    In /img dir scrapper saves all photos
    In /json dir scrapper saves info from Query in JSON format
