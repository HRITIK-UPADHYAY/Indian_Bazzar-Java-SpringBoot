package com.backend.IndianBazzar.ResponseDTO;

import com.backend.IndianBazzar.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDTO {
    private String cardNo;

    private CardType cardType;
}
