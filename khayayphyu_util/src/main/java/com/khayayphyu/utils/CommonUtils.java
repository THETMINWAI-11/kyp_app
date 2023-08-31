package com.khayayphyu.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.khayayphyu.utils.common.CommonConstant;

public class CommonUtils {
	public static boolean validInteger(Integer value) {
		return value != null && value.intValue() > 0;
	}

	public static boolean validLong(Long value) {
		return value != null && value.longValue() > 0;
	}

	public static boolean validDouble(Double value) {
		return value != null && value.doubleValue() > 0;
	}

	public static boolean validBigDecimal(BigDecimal value) {
		return value != null && value.compareTo(BigDecimal.ZERO) > 0;
	}

	public static boolean validString(String value) {
		return value != null && !value.trim().isEmpty();
	}

	public static void removeAll(Collection<?> list) {
		if (CollectionUtils.isEmpty(list))
			return;
		list.removeIf(c -> true);
	}
	
	private static <T> void remove(List<T> source, Predicate<T> filter) {
		source.removeIf(filter);
	}

	public static <T> T defaultOnNull(T t, T def) {
		return t == null ? def : t;
	}

	public static <T extends EntityUtil> boolean isSameId(T t1, T t2) {
		if (t1 == null || t2 == null)
			return false;
		return t1.getId().equals(t2.getId());
	}

	@SuppressWarnings("rawtypes")
	public static boolean validList(List value) {
		return value != null && !value.isEmpty();
	}

	public static <I, V> List<I> mapToList(Collection<V> list, Function<V, I> convertor) {
		if (CollectionUtils.isEmpty(list))
			return Collections.emptyList();

		return list.stream().map(convertor).filter(d -> d != null).collect(Collectors.toList());
	}

	public static <I, V> List<I> mapToList(Stream<V> stream, Function<V, I> convertor) {
		return stream.map(convertor).collect(Collectors.toList());
	}

	public static <I, V> List<I> mapToMutableList(Collection<V> list, Function<V, I> convertor) {
		if (CollectionUtils.isEmpty(list))
			return new ArrayList<I>();

		return list.stream().map(convertor).filter(d -> d != null).collect(Collectors.toList());
	}

	public static <I, V> List<I> mapToMutableList(Collection<V> list, Function<V, I> convertor,
			Function<I, I> additional) {
		if (CollectionUtils.isEmpty(list))
			return new ArrayList<I>();

		return list.stream().map(convertor).filter(d -> d != null).map(additional).collect(Collectors.toList());
	}

	public static <I> void addToList(List<I> list, List<I> elements) {
		if (CollectionUtils.isEmpty(elements))
			return;
		elements.forEach(list::add);
	}

	public static <I> void clearList(List<I> list) {
		if (CollectionUtils.isEmpty(list))
			return;

		CommonUtils.removeAll(list);
	}

	public static <I, V> List<I> mapToList(Collection<V> list, Function<V, I> convertor, Function<I, I> additional) {
		if (CollectionUtils.isEmpty(list))
			return Collections.emptyList();

		return list.stream().map(convertor).filter(d -> d != null).map(additional).collect(Collectors.toList());
	}

	public static <V, I> Map<I, List<V>> classify(List<V> list, Function<V, I> classifier) {
		if (CollectionUtils.isEmpty(list))
			return new HashMap<>();
		return list.stream().filter(obj -> obj != null).collect(Collectors.groupingBy(classifier, Collectors.toList()));
	}

	public static <V, I> Map<I, Double> classifySumDouble(List<V> list, Function<V, I> classifier,
			ToDoubleFunction<V> adder) {
		Map<I, List<V>> map = classify(list, classifier);
		Map<I, Double> resultMap = new HashMap<>();
		for (Entry<I, List<V>> entry : map.entrySet()) {
			resultMap.put(entry.getKey(), entry.getValue().stream().mapToDouble(adder).sum());
		}
		return resultMap;
	}

	public static Double averageDouble(List<Double> numberList) {
		if (CollectionUtils.isEmpty(numberList))
			return 0D;
		return numberList.stream().mapToDouble(d -> d).sum() / numberList.size();
	}

	public static <V> long sumLong(List<V> list, Function<V, Long> adder) {
		if (CollectionUtils.isEmpty(list))
			return 0;
		return list.stream().mapToLong(obj -> adder.apply(obj)).sum();
	}
	
	public static <V> int sumInteger(List<V> list, Function<V, Integer> adder) {
		if (CollectionUtils.isEmpty(list))
			return 0;
		return list.stream().mapToInt(obj -> adder.apply(obj)).sum();
	}

	public static <V> double sumDouble(List<V> list, Function<V, Double> adder) {
		if (CollectionUtils.isEmpty(list))
			return 0;
		return list.stream().mapToDouble(obj -> notNullValue(adder.apply(obj))).sum();
	}

	public static <V> String listToString(List<V> list, Function<V, String> mapper) {
		if (CollectionUtils.isEmpty(list))
			return "-";
		return list.stream().map(obj -> mapper.apply(obj)).filter(StringUtils::isNotEmpty)
				.collect(Collectors.joining(","));
	}

	public static <V> String listToDistictString(List<V> list, Function<V, String> mapper) {
		if (CollectionUtils.isEmpty(list))
			return "-";
		Set<String> result = list.stream().map(obj -> mapper.apply(obj)).collect(Collectors.toSet());
		return result.stream().collect(Collectors.joining(", "));
	}

	public static <V, I> Map<I, V> toMap(List<V> list, Function<V, I> keySupplier) {
		Map<I, V> map = new HashMap<>();
		if (CollectionUtils.isEmpty(list))
			return map;
		list.stream().forEach(value -> {
			I key = keySupplier.apply(value);
			if (map.containsKey(key))
				return;
			map.put(key, value);
		});
		return map;
	}

	public static <I, V> V map(I i, Function<I, V> convertor) {
		if (i == null)
			return null;
		return convertor.apply(i);
	}

	public static <I, V> V map(Optional<I> i, Function<I, V> convertor) {
		if (i == null || !i.isPresent())
			return null;
		return convertor.apply(i.get());
	}

	public static <I> int countList(List<I> list, Function<I, Integer> supplier) {
		if (CollectionUtils.isEmpty(list))
			return 0;
		return list.stream().mapToInt(supplier::apply).sum();
	}

	public static <I, V> List<V> toList(List<I> list, Function<I, V> supplier) {
		if (CollectionUtils.isEmpty(list))
			return Collections.emptyList();
		return list.stream().map(supplier::apply).filter(data -> data != null).collect(Collectors.toList());
	}

	public static long multiple(Long num1, Long num2) {
		long t1 = num1 != null ? num1 : 1;
		long t2 = num2 != null ? num2 : 1;
		return t1 * t2;
	}

	public static double multiple(Double num1, Double num2) {
		double t1 = num1 != null ? num1 : 1;
		double t2 = num2 != null ? num2 : 1;
		return t1 * t2;
	}

	public static <I, V> Set<V> toSet(List<I> list, Function<I, V> supplier) {
		if (CollectionUtils.isEmpty(list))
			return Collections.emptySet();
		return list.stream().map(supplier::apply).filter(data -> data != null).collect(Collectors.toSet());
	}

	public static <I, V> List<V> toList(Set<I> list, Function<I, V> supplier) {
		if (CollectionUtils.isEmpty(list))
			return Collections.emptyList();
		return list.stream().map(supplier::apply).filter(data -> data != null).collect(Collectors.toList());
	}

	public static <I> List<I> filter(List<I> list, Predicate<I> filter) {
		if (CollectionUtils.isEmpty(list))
			return Collections.emptyList();
		return list.stream().filter(filter).collect(Collectors.toList());
	}

//	public static String UnicodeToZawgyi(String string) {
//		if (string != null) {
//			final TransliterateU2Z u2Z = new TransliterateU2Z("Unicode to Zawgyi");
//			String result = u2Z.convert(string);
//			return result;
//		}
//		return "";
//	}

	public static Long notNullValue(Long t) {
		return t != null ? t : 0;
	}
	
	public static String notNullValue(String str) {
		return StringUtils.isBlank(str) ? "" : str;
	}

	public static Integer notNullValue(Integer t) {
		return t != null ? t : 0;
	}

	public static Double notNullValue(Double t) {
		return t != null ? t : 0;
	}

//	public static String ZawgyiToUnicode(String string) {
//		if (string != null) {
//			final TransliterateZ2U z2U = new TransliterateZ2U("Zawgyi to Unicode");
//			String result = z2U.convert(string);
//			return result;
//		}
//		return "";
//	}

	public static <T> T max(List<T> list, Comparator<T> comparator) {
		if (CollectionUtils.isEmpty(list))
			return null;
		return list.stream().max(comparator).get();
	}

	public static <T> T min(List<T> list, Comparator<T> comparator) {
		if (CollectionUtils.isEmpty(list))
			return null;
		return list.stream().min(comparator).get();
	}

	public static <T> List<T> toList(Iterable<T> iterable) {
		if (iterable == null)
			return Collections.emptyList();
		return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
	}

	public static String generateToken() {
		Random random = new Random();
		int number = random.nextInt(999999);
		String otpCode = String.format("%06d", number);
		return otpCode;
	}

	public static String generateRandomNo() {
		return Long.toHexString(System.currentTimeMillis()).toUpperCase();
	}

	public static boolean validNumber(Number value) {
		return value != null && value.floatValue() > 0;
	}

	public static String formatNumberWithDecimalForSalaryReport(BigDecimal val) {
		if (!validNumber(val)) {
			return "-";
		}

		val = val.setScale(3, RoundingMode.HALF_UP);

		return new DecimalFormat("#,###.00").format(val);
	}

	public static BigDecimal formatCurrencyScale(BigDecimal amount) {
		if (!validBigDecimal(amount)) {
			return BigDecimal.ZERO;
		}
		return amount.setScale(0, RoundingMode.HALF_UP);
	}

	public static String formatNumberForDriverSalaryReport(BigDecimal val) {
		if (!validBigDecimal(val)) {
			return "-";
		}
		return new DecimalFormat("#,##0").format(formatCurrencyScale(val));
	}

	public static String changeDateToString(String format, Date date) {
		if (date == null) {
			return "";
		}

		if (format == null || format.trim().isEmpty()) {
			format = CommonConstant.STD_DATE_TIME_FORMAT;
		}

		return new SimpleDateFormat(format).format(date);
	}
	
	public static <Z, U> boolean isContains(List<U> list, Z target, BiPredicate<Z, U> comparator) {
		if(CollectionUtils.isEmpty(list) || target == null)
			return false;
		
		for(U u : list) {
			if(comparator.test(target, u))
				return true;
		}
		return false;
	}
	
	public static <Z, U> boolean isAnyContains(List<U> sourceList, List<Z> targetList, BiPredicate<Z, U> comparator) {
		if(CollectionUtils.isEmpty(sourceList) || CollectionUtils.isEmpty(targetList))
			return false;
		
		long totalMatch = targetList.stream().map(t -> CommonUtils.isContains(sourceList, t, comparator)).filter(a -> a).count();
		return totalMatch > 0;
	}

	public static Date stringToDate(String format, String dateString) {
		if (dateString == null || dateString.trim().isEmpty()) {
			return null;
		}
		if (format == null || format.trim().isEmpty()) {
			format = CommonConstant.STD_DATE_FORMAT;
		}
		try {
			return new SimpleDateFormat(format).parse(dateString);
		} catch (Exception e) {

		}
		return null;
	}
	
//	public static void populateWorkbookRow(Row row, List<String> strs) {
//		for(int i=0;i<strs.size();i++) {
//			Cell cell = row.createCell(i);
//			cell.setCellValue(strs.get(i));
//		}
//	}
}
