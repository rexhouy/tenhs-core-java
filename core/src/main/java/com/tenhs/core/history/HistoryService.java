package com.tenhs.core.history;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryService<T> {

    HistoryMapper historyMapper;

    public HistoryService(HistoryMapper historyMapper) {
        this.historyMapper = historyMapper;
    }

    public void save(Object obj, String memo, Long uid) {
        History hist = new History();
        hist.setArchive(new ObjectMapper().convertValue(obj, HashMap.class))
                .setArchiveableId(getId(obj))
                .setArchiveableType(obj.getClass().getName())
                .setMemo(memo)
                .setUserId(uid);
        historyMapper.insert(hist);
    }

    public List<T> list(Long id, Class<T> type) {
        List<History> hists = historyMapper.findHistory(id, type.getName());
        return hists.stream().map(h -> {
            return new ObjectMapper().convertValue(h.getArchive(), type);
        }).collect(Collectors.toList());
    }

    private Long getId(Object obj) {
        try {
            Method getter = obj.getClass().getMethod("getId");
            return (Long) getter.invoke(obj);
        } catch (Exception e) {
            return null;
        }
    }
}
