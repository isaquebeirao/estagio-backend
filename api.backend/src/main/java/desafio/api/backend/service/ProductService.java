package desafio.api.backend.service;



import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import desafio.api.backend.model.Product;

import desafio.api.backend.model.dto.ListProductDTO;
import desafio.api.backend.model.dto.PaymentUrlDTO;
import desafio.api.backend.model.dto.RegisterProductDTO;
import desafio.api.backend.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Value("${mercadopago.token}")
    private String accessToken;

    public ResponseEntity createProduct (RegisterProductDTO product) {
        productRepository.save(new Product(product));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<ListProductDTO>> listProduct () {
        List<ListProductDTO> list = productRepository.findAll().stream().map(ListProductDTO::new).toList();
        return ResponseEntity.ok(list);
    }

    public ResponseEntity selectProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        MercadoPagoConfig.setAccessToken(accessToken);

        try {

            PreferenceItemRequest itemRequest =
                    PreferenceItemRequest.builder()
                            .id(id.toString())
                            .title(product.getName())
                            .description(product.getDescription())
                            .quantity(1)
                            .currencyId("BRL")
                            .unitPrice(BigDecimal.valueOf(product.getValue().floatValue()))
                            .build();

            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(itemRequest);

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .build();

            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            String paymentUrl = preference.getInitPoint();

            PaymentUrlDTO response = new PaymentUrlDTO(paymentUrl);

            return ResponseEntity.ok(response);
        } catch (MPException e) {
            throw new RuntimeException(e);
        } catch (MPApiException e) {
            throw new RuntimeException(e);
        }

    }

}
