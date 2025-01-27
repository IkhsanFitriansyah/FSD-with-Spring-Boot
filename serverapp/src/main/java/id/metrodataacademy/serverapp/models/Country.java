package id.metrodataacademy.serverapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Integer id;

    @Column(name = "country_code",nullable = false, length = 2, unique = true)
    private String code;

    @Column(name = "country_name", nullable = false, length = 25)
    private String name;

    @ManyToOne //  Relasi Many to One dari Country ke Region
    @JoinColumn(name = "region_id") // Menghubungkan region_id kedalam Country
    private Region region;

    
}
