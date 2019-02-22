package net.andreinc.mockneat.types.enums;

public enum CelebrityType {
    ACTORS(new DictType[]{ DictType.ACTORS }),
    ACTRESSES(new DictType[]{ DictType.ACTRESSES }),
    JAZZ_ARTISTS(new DictType[]{ DictType.JAZZ_ARTISTS }),
    ROCK_STARS(new DictType[]{ DictType.ROCK_STARS }),
    UK_PRIME_MINISTERS(new DictType[]{ DictType.UK_PRIME_MINISTERS }),
    US_PRESIDENTS(new DictType[]{ DictType.US_PRESIDENTS }),

    // COMPOSED

    SINGERS(new DictType[]{
            DictType.ROCK_STARS,
            DictType.JAZZ_ARTISTS
    }),

    POLITICIANS(new DictType[]{
            DictType.US_PRESIDENTS,
            DictType.UK_PRIME_MINISTERS
    });

    private final DictType[] dictionaries;

    CelebrityType(DictType[] dictionaries) {
        this.dictionaries = dictionaries;
    }

    public DictType[] getDictionaries() {
        return dictionaries;
    }
}
