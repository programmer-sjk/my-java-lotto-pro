package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class ManualLottoGenerator implements LottoGenerator {
    private final List<Integer> numbers;

    public ManualLottoGenerator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public LottoTicket create() {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::get)
                .collect(Collectors.toList());

        return LottoTicket.create(lottoNumbers);
    }

//    public List<LottoTicket> createMany(List<String> inputs) {
//        List<LottoTicket> tickets = new ArrayList<>();
//        for (String input : inputs) {
//            List<Integer> numbers = ManualLottoGenerator.toNumbers(input);
//            tickets.add(LottoTicket.create(convertLottoNumber(numbers)));
//        }
//
//        return tickets;
//    }
}
