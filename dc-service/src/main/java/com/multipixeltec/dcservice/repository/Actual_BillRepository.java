package com.multipixeltec.dcservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.multipixeltec.dcservice.dto.PageDetails;
import com.multipixeltec.dcservice.model.Actual_Bill;

public interface Actual_BillRepository extends JpaRepository<Actual_Bill, Long> {

	@Query(value = "SELECT * FROM actual_bill WHERE date > :#{#page.from} AND date<= CONCAT(:#{#page.to}, 'T23:59:59')", nativeQuery = true)
	Page<Actual_Bill> findAllByDate(PageDetails page, Pageable pageable);

	@Query(value = "SELECT * FROM actual_bill  where " + "( name like %:#{#page.text}% OR "
			+ " agency like %:#{#page.text}% OR " + "patientId like %:#{#page.text}% OR "
			+ "paid like %:#{#page.text}% OR " + "due like %:#{#page.text}% OR "
			+ " packageName like %:#{#page.text}%) AND date > :#{#page.from} AND date <= CONCAT(:#{#page.to}, 'T23:59:59')", nativeQuery = true)
	Page<Actual_Bill> findAllByDateAndText(PageDetails page, Pageable pageable);

	void deleteByPatientId(String regNo);

}